package TP;

import TP.Configuration.ConfigurationFile;
import TP.Configuration.CutCriteriaMethod;
import TP.Configuration.DataConfiguration;
import TP.Configuration.SelectionMethod;
import TP.Constants.Constants;
import TP.Helpers.CSVImportHelper;
import TP.Helpers.Factories.*;
import TP.Interfaces.*;
import TP.Models.CutCriteria.BaseCutCriteria;
import TP.Models.FillAll;
import TP.Models.Generation;
import TP.Models.Genetics.Selections.CombinedSelection;
import TP.Models.Player.BasePlayer;
import TP.Models.Genetics.Selections.Selection;
import TP.Models.Equipment;
import TP.Models.Genetics.Mutations.Mutation;
import TP.Services.RedisService;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class Game {

    private List<ConfigurationFile> confs;
    private List<OutputStream> outputStreams;
    private RedisService service;

    private Map<Integer, Equipment> helmets;
    private Map<Integer, Equipment> fronts;
    private Map<Integer, Equipment> gloves;
    private Map<Integer, Equipment> weapons;
    private Map<Integer, Equipment> boots;

    private CombinedSelection parentsSelection;
    private CombinedSelection replacementSelection;

    private IFillMethod fillMethod;

    private Mutation mutation;

    private BaseCutCriteria cutCriteria;

    private BasePlayer player;

    private int poblationNumber;

    private List<BasePlayer> initialPopulation;

    private int generationNumber;
    PrintWriter writer;

    public Game(DataConfiguration dataConf){

        ICSVImportHelper importHelper = new CSVImportHelper();

        importHelper.setData(Constants.Equipment.boots,dataConf.getBootsPath());
        System.out.println("Boots Loaded");
        importHelper.setData(Constants.Equipment.helmet,dataConf.getHelmetPath());
        System.out.println("Helmets Loaded");
        importHelper.setData(Constants.Equipment.weapons,dataConf.getWeaponsPath());
        System.out.println("Weapons Loaded");
        importHelper.setData(Constants.Equipment.gloves,dataConf.getGlovesPath());
        System.out.println("Gloves Loaded");
        importHelper.setData(Constants.Equipment.front,dataConf.getFrontPath());
        System.out.println("Fronts Loaded");

    }
    public Game(List<ConfigurationFile> configs, List<OutputStream> outputStreams){
        this.confs = configs;
        service = new RedisService();
        this.outputStreams = outputStreams;
        //Cargar la data del servicio de db
        this.prepareEquipment();
    }

    private void prepareEquipment() {
        this.helmets = service.getData(Constants.Equipment.helmet);
        this.fronts = service.getData(Constants.Equipment.front);
        this.gloves = service.getData(Constants.Equipment.gloves);
        this.weapons = service.getData(Constants.Equipment.weapons);
        this.boots = service.getData(Constants.Equipment.boots);
        this.service.setBoots(boots);
        this.service.setFronts(fronts);
        this.service.setGloves(gloves);
        this.service.setHelmets(helmets);
        this.service.setWeapons(weapons);
    }

    private void generateRandomPopulation() {
        List<BasePlayer> randomPopulation = new LinkedList<>();
        BasePlayer playerAux;
        Random rand = new Random();
        for(int i = 0; i < this.poblationNumber; i++) {
            playerAux = ClassesFactory.givePlayer(player.getName());
            playerAux.getEquipment().add(service.getFronts().get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(service.getHelmets().get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(service.getGloves().get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(service.getBoots().get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(service.getWeapons().get(rand.nextInt(1000000)));
            playerAux.setHeight(ThreadLocalRandom.current().nextInt(130, 201));
            playerAux.calculateAll();
            randomPopulation.add(playerAux);
        }
        this.initialPopulation = randomPopulation;
    }

    public void run() {
        for(int i = 0; i < confs.size(); i++){
            setupConfiguration(i);
            generateRandomPopulation();
            System.out.println("random population generated");
            Generation generation = new Generation(initialPopulation, service);
            while (!this.cutCriteria.cutProgram(generation)) {
                System.out.println("running generation " + generation.getGenerationNumber());
                //generar la nueva generación
                List<BasePlayer> newPopulation =
                        fillMethod.fill(generation.getCurrentPopulation(), parentsSelection, replacementSelection);
                //comparar resultados y asignar nueva generación
                generation.nextGeneration(newPopulation);
                //imprimir al socket si resultado de la generación actual
                if (writer != null) {
                    writer.println(String.format(Locale.US, "%08.4f", generation.getCurrentFitness()));
                    writer.flush();
                }
            }
            System.out.println("Mejor equipamiento: " + generation.getBestFitness().getChromosome().toString());
            //mandar señal al graficador de que terminamos
            if (writer != null) {
                writer.println(String.format(Locale.US, "%08.4f", -1.0));
                writer.flush();
                try { outputStreams.get(i).close(); }
                catch (IOException e){ e.printStackTrace(); }
            }
        }
    }

    public CombinedSelection setupSelection(SelectionMethod sel1, SelectionMethod sel2){
        Selection parentSel1 = SelectionMethodFactory.giveSelection(sel1, this.generationNumber,
                sel1.getArg1(), sel1.getArg2());
        Selection parentSel2 = SelectionMethodFactory.giveSelection(sel2, this.generationNumber,
                sel2.getArg1(), sel2.getArg2());
        //setear la cantidad de individuos que debe agarrar cada método de selección
        parentSel1.setK((int) (this.generationNumber * parentSel1.getPercentage()));
        parentSel2.setK((int) (this.generationNumber * ( 1 - parentSel1.getPercentage())));
        return new CombinedSelection(parentSel1, parentSel2);
    }

    public BaseCutCriteria setupCutCriteria(ConfigurationFile conf){
        CutCriteriaMethod criteriaMethod = conf.getCriteria();
        return CutCriteriaFactory.giveCriteria(criteriaMethod);
    }

    public void setupConfiguration(int index){
        ConfigurationFile conf = this.confs.get(index);
        //N
        this.poblationNumber = conf.getPopulation();
        //K
        this.generationNumber = conf.getGenerationNumber();
        //Métodos de selección 1 y 2
        this.parentsSelection = setupSelection(conf.getFatherMethod_1(), conf.getFatherMethod_2());
        //Métodos de selección 3 y 4
        this.replacementSelection = setupSelection(conf.getIndividualMethod_1(), conf.getIndividualMethod_2());
        //Mutación
        this.mutation = MutationFactory.giveMutation(conf.getMutation());
        //Método de cruza
        ICrossover crossover = CrossoverFactory.giveCrossover(conf.getCrossoverMethod());
        //Método de implementación
        this.fillMethod = FillMethodFactory.giveMethod(conf.getFillMethod(), crossover, mutation, service);
        //Tipo de player
        this.player = ClassesFactory.givePlayer(conf.getIndividualClass());
        //Criterio de corte
        this.cutCriteria = setupCutCriteria(conf);
        //Escritor a socket o archivo
        if(outputStreams.get(index) != null) this.writer = new PrintWriter(outputStreams.get(index));
    }

}
