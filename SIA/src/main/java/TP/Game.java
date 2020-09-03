package TP;

import TP.Configuration.ConfigurationFile;
import TP.Constants.Constants;
import TP.Helpers.Factories.ClassesFactory;
import TP.Helpers.Factories.CrossoverFactory;
import TP.Helpers.Factories.MutationFactory;
import TP.Helpers.Factories.SelectionMethodFactory;
import TP.Interfaces.*;
import TP.Models.BaseCutCriteria;
import TP.Models.BasePlayer;
import TP.Models.Genetics.Chromosome;
import TP.Models.Selection;
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
    private IService service;

    private Map<Integer, Equipment> helmets;
    private Map<Integer, Equipment> fronts;
    private Map<Integer, Equipment> gloves;
    private Map<Integer, Equipment> weapons;
    private Map<Integer, Equipment> boots;

    private Selection fatherMethod_1;
    private Selection fatherMethod_2;
    private Selection individualMethod_1;
    private Selection individualMethod_2;

    private ICrossover crossoverMethod;

    private Mutation mutation;

    private BaseCutCriteria cutCriteria;

    private BasePlayer player;

    private int poblationNumber;

    private List<BasePlayer> poblation;

    private List<BasePlayer> currentGeneration;

    public Game(ConfigurationFile conf) {

        this.conf = conf;
        service = new RedisService();
        prepareEquipment();

        this.fatherMethod_1 = SelectionMethodFactory.giveSelection(conf.getFatherMethod_1());
        this.fatherMethod_2 = SelectionMethodFactory.giveSelection(conf.getFatherMethod_2());
        this.individualMethod_1 = SelectionMethodFactory.giveSelection(conf.getIndividualMethod_1());
        this.individualMethod_2 = SelectionMethodFactory.giveSelection(conf.getIndividualMethod_2());

        this.crossoverMethod = CrossoverFactory.giveCrossover(conf.getCrossoverMethod());
        this.mutation = MutationFactory.giveMutation(conf.getMutation());

        this.player = ClassesFactory.givePlayer(conf.getIndividualClass());

        List<BaseCutCriteria> criterias = new LinkedList<BaseCutCriteria>();

        criterias.add(conf.getAcceptableSolutionCriteria());
        criterias.add(conf.getContentCriteria());
        criterias.add(conf.getNumberOfGenerationsCriteria());
        criterias.add(conf.getTimeCriteria());
        criterias.add(conf.getStructureCriteria());

        this.cutCriteria = prepareCutCriteria(criterias);
        this.poblationNumber = conf.getPoblation();
    }

    private void prepareEquipment() {
        this.helmets = service.getData(Constants.Equipment.helmet);
        this.fronts = service.getData(Constants.Equipment.front);
        this.gloves = service.getData(Constants.Equipment.gloves);
        this.weapons = service.getData(Constants.Equipment.weapons);
        this.boots = service.getData(Constants.Equipment.boots);
    }

    private BaseCutCriteria prepareCutCriteria(List<BaseCutCriteria> criterias) {
        return criterias.stream().filter(x -> x.isInUse()).findFirst().orElse(null);
    }

    private void mutate(Chromosome chromosome) {
        double rand = ThreadLocalRandom.current().nextDouble(0, 1);
        if (rand > this.mutation.getMutationProbability()) {
            chromosome = mutation.mutate(chromosome);
        }
    }

    private void generateRandomPoblation(){
        List<BasePlayer> randomPoblation = new LinkedList<BasePlayer>();
        int i = 0;
        BasePlayer playerAux;
        Random rand = new Random();
        int floorHeight = 130;

        while(i < this.poblationNumber){
            playerAux = ClassesFactory.givePlayer(player.getName());
            playerAux.getEquipment().add(fronts.get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(helmets.get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(gloves.get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(boots.get(rand.nextInt(1000000)));
            playerAux.getEquipment().add(weapons.get(rand.nextInt(1000000)));
            playerAux.setHeight(rand.nextInt(70) + floorHeight);
            randomPoblation.add(playerAux);
            i++;
        }
        this.poblation= randomPoblation;
    }

//    private void selection(Selection selection_1, Selection selection_2){
//
//    }
}
