package TP;

import TP.Configuration.ConfigurationFile;
import TP.Constants.Constants;
import TP.Helpers.Factories.ClassesFactory;
import TP.Helpers.Factories.CrossoverFactory;
import TP.Helpers.Factories.MutationFactory;
import TP.Helpers.Factories.SelectionMethodFactory;
import TP.Interfaces.*;
import TP.Models.CutCriteria.BaseCutCriteria;
import TP.Models.FillAll;
import TP.Models.Generation;
import TP.Models.Genetics.Chromosome;
import TP.Models.Genetics.Selections.CombinedSelection;
import TP.Models.Player.BasePlayer;
import TP.Models.Genetics.Selections.Selection;
import TP.Models.Equipment;
import TP.Models.Genetics.Mutations.Mutation;
import TP.Services.RedisService;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class Game {

    private ConfigurationFile conf;
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

    PrintWriter writer = null;

    public Game(ConfigurationFile conf, OutputStream outputStream) {
        this.conf = conf;

        service = new RedisService();

        this.poblationNumber = conf.getPoblation();
        this.generationNumber = conf.getGenerationNumber();

        Selection fatherMethod1 = SelectionMethodFactory.giveSelection(conf.getFatherMethod_1(), this.generationNumber,conf.getFatherMethod_1().getBoltzmanT0(),conf.getFatherMethod_1().getBoltzmanTC());
        Selection fatherMethod2 = SelectionMethodFactory.giveSelection(conf.getFatherMethod_2(), this.poblationNumber,conf.getFatherMethod_2().getBoltzmanT0(),conf.getFatherMethod_2().getBoltzmanTC());
        fatherMethod1.setK((int) (this.generationNumber * fatherMethod1.getPercentage()));
        fatherMethod2.setK((int) (this.generationNumber * ( 1 - fatherMethod1.getPercentage())));

        Selection replacementMethod1 = SelectionMethodFactory.giveSelection(conf.getIndividualMethod_1(), this.generationNumber,conf.getIndividualMethod_1().getBoltzmanT0(),conf.getIndividualMethod_1().getBoltzmanTC());
        Selection replacementMethod2 = SelectionMethodFactory.giveSelection(conf.getIndividualMethod_2(), this.generationNumber,conf.getIndividualMethod_2().getBoltzmanT0(),conf.getIndividualMethod_2().getBoltzmanTC());

        //setear la cantidad de individuos que debe agarrar cada método de selección
        fatherMethod1.setK(     (int) (this.generationNumber *            fatherMethod1.getPercentage()));
        fatherMethod2.setK(     (int) (this.generationNumber * ( 1 -      fatherMethod1.getPercentage())));
        replacementMethod1.setK((int) (this.generationNumber *       replacementMethod1.getPercentage()));
        replacementMethod2.setK((int) (this.generationNumber * ( 1 - replacementMethod1.getPercentage())));

        this.parentsSelection = new CombinedSelection(fatherMethod1, fatherMethod2);
        this.replacementSelection = new CombinedSelection(replacementMethod1, replacementMethod2);

        this.mutation = MutationFactory.giveMutation(conf.getMutation());
        ICrossover crossover = CrossoverFactory.giveCrossover(conf.getCrossoverMethod());
        this.fillMethod = new FillAll(crossover, mutation, service);
        this.player = ClassesFactory.givePlayer(conf.getIndividualClass());

        List<BaseCutCriteria> criterias = new LinkedList<>();

        criterias.add(conf.getAcceptableSolutionCriteria());
        criterias.add(conf.getContentCriteria());
        criterias.add(conf.getNumberOfGenerationsCriteria());
        criterias.add(conf.getTimeCriteria());
        criterias.add(conf.getStructureCriteria());

        if(outputStream != null) writer = new PrintWriter(outputStream);
        this.cutCriteria = prepareCutCriteria(criterias);
        prepareEquipment();
    }

    private void prepareEquipment() {
        this.helmets = service.getData(Constants.Equipment.helmet);
        this.fronts = service.getData(Constants.Equipment.front);
        this.gloves = service.getData(Constants.Equipment.gloves);
        this.weapons = service.getData(Constants.Equipment.weapons);
        this.boots = service.getData(Constants.Equipment.boots);

        Map<Integer, Equipment> helmets= this.helmets;
        Map<Integer, Equipment> fronts = this.fronts;
        Map<Integer, Equipment> gloves = this.gloves;
        Map<Integer, Equipment> weapons= this.weapons;
        Map<Integer, Equipment> boots = this.boots;

        //helmets = service.getData(Constants.Equipment.helmet);
        //fronts = service.getData(Constants.Equipment.front);
        //gloves = service.getData(Constants.Equipment.gloves);
        //weapons = service.getData(Constants.Equipment.weapons);
        //boots = service.getData(Constants.Equipment.boots);

        this.service.setBoots(boots);
        this.service.setFronts(fronts);
        this.service.setGloves(gloves);
        this.service.setHelmets(helmets);
        this.service.setWeapons(weapons);
    }

    private BaseCutCriteria prepareCutCriteria(List<BaseCutCriteria> criterias) {
        return criterias.stream().filter(BaseCutCriteria::isInUse).findFirst().orElse(null);
    }

    private void generateRandomPopulation() {
        List<BasePlayer> randomPopulation = new LinkedList<>();
        int i = 0;
        BasePlayer playerAux;
        Random rand = new Random();
        

        while (i < this.poblationNumber) {
            playerAux = ClassesFactory.givePlayer(player.getName());
            playerAux.getEquipment().add(service.getFronts().get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(service.getHelmets().get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(service.getGloves().get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(service.getBoots().get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(service.getWeapons().get(rand.nextInt(1000000)));

            playerAux.setHeight(ThreadLocalRandom.current().nextInt(130, 201));
            playerAux.calculateAll();
            randomPopulation.add(playerAux);
            i++;
        }
        this.initialPopulation = randomPopulation;
    }

    public void run() {
        generateRandomPopulation();
        System.out.println("random population generated");
        Generation generation = new Generation(initialPopulation, service);
        while (!this.cutCriteria.cutProgram(generation)) {
            System.out.println("running generation " + generation.getGenerationNumber());
            List<BasePlayer> newPopulation =
                    fillMethod.fill(generation.getCurrentPopulation(), parentsSelection, replacementSelection);
            generation.nextGeneration(newPopulation);
            if(writer != null) {
                writer.println(String.format(Locale.US, "%08.4f", generation.getCurrentFitness()));
                writer.flush();
            }
        }
        //mandar señal al graficador de que terminamos
        if(writer != null) {
            writer.println(String.format(Locale.US, "%08.4f", -1.0));
            writer.flush();
        }
    }

}
