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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    public Game(ConfigurationFile conf) {

        this.conf = conf;
        service = new RedisService();
        prepareEquipment();
        this.poblationNumber = conf.getPoblation();
        Selection fatherMethod1 = SelectionMethodFactory.giveSelection(conf.getFatherMethod_1(), this.generationNumber);
        Selection fatherMethod2 = SelectionMethodFactory.giveSelection(conf.getFatherMethod_2(), this.poblationNumber);
        this.parentsSelection = new CombinedSelection(fatherMethod1, fatherMethod2);

        Selection replacementMethod1 = SelectionMethodFactory.giveSelection(conf.getIndividualMethod_1(), this.generationNumber);
        Selection replacementMethod2 = SelectionMethodFactory.giveSelection(conf.getIndividualMethod_2(), this.poblationNumber);
        this.replacementSelection = new CombinedSelection(replacementMethod1, replacementMethod2);

        this.fillMethod = new FillAll(CrossoverFactory.giveCrossover(conf.getCrossoverMethod()));
        this.mutation = MutationFactory.giveMutation(conf.getMutation());

        this.player = ClassesFactory.givePlayer(conf.getIndividualClass());

        List<BaseCutCriteria> criterias = new LinkedList<>();

        criterias.add(conf.getAcceptableSolutionCriteria());
        criterias.add(conf.getContentCriteria());
        criterias.add(conf.getNumberOfGenerationsCriteria());
        criterias.add(conf.getTimeCriteria());
        criterias.add(conf.getStructureCriteria());

        this.poblationNumber = conf.getPoblation();
        this.cutCriteria = prepareCutCriteria(criterias);
        this.generationNumber = conf.getGenerationNumber();
    }

    private void prepareEquipment() {
        this.helmets = service.getData(Constants.Equipment.helmet);
        this.fronts = service.getData(Constants.Equipment.front);
        this.gloves = service.getData(Constants.Equipment.gloves);
        this.weapons = service.getData(Constants.Equipment.weapons);
        this.boots = service.getData(Constants.Equipment.boots);

        this.service.setBoots(this.boots);
        this.service.setFronts(this.fronts);
        this.service.setGloves(this.gloves);
        this.service.setHelmets(this.helmets);
        this.service.setWeapons(this.weapons);
    }

    private BaseCutCriteria prepareCutCriteria(List<BaseCutCriteria> criterias) {
        return criterias.stream().filter(BaseCutCriteria::isInUse).findFirst().orElse(null);
    }

    private void mutate(Chromosome chromosome) {
        double rand = ThreadLocalRandom.current().nextDouble(0, 1);
        if (rand > this.mutation.getMutationProbability()) {
            chromosome = mutation.mutate(chromosome);
        }
    }

    private void generateRandomPopulation() {
        List<BasePlayer> randomPopulation = new LinkedList<>();
        int i = 0;
        BasePlayer playerAux;
        Random rand = new Random();
        int floorHeight = 130;

        while (i < this.poblationNumber) {
            playerAux = ClassesFactory.givePlayer(player.getName());
            playerAux.getEquipment().add(fronts.get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(helmets.get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(gloves.get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(boots.get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(weapons.get(rand.nextInt(1000000)));
            //playerAux.setHeight(rand.nextInt(70) + floorHeight);
            playerAux.setHeight(ThreadLocalRandom.current().nextInt(130, 201));
            playerAux.CalculateAll();
            randomPopulation.add(playerAux);
            i++;
        }
        this.initialPopulation = randomPopulation;
    }

    public void run() {
        generateRandomPopulation();
        System.out.println("random population generated");
        Generation generation = new Generation(initialPopulation, fillMethod, service,
                                                parentsSelection, replacementSelection);
        while (!this.cutCriteria.cutProgram(generation)) {
            System.out.println("running generation" + generation.getGenerationNumber());
            generation.nextGeneration();
        }
    }

}
