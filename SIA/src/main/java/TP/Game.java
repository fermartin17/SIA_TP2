package TP;

import TP.Configuration.ConfigurationFile;
import TP.Configuration.MutationConfiguration;
import TP.Configuration.SelectionMethod;
import TP.Constants.Constants;
import TP.Helpers.Factories.ClassesFactory;
import TP.Helpers.Factories.CrossoverFactory;
import TP.Helpers.Factories.MutationFactory;
import TP.Helpers.Factories.SelectionMethodFactory;
import TP.Interfaces.*;
import TP.Models.BaseCutCriteria;
import TP.Models.BasePlayer;
import TP.Models.BaseSelection;
import TP.Models.CutCriteria.*;
import TP.Models.Equipment;
import TP.Services.RedisService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {

    private ConfigurationFile conf;
    private IService service;

    private Map<Integer, Equipment> helmets;
    private Map<Integer, Equipment> front;
    private Map<Integer, Equipment> gloves;
    private Map<Integer, Equipment> weapons;
    private Map<Integer, Equipment> boots;

    private ISelection fatherMethod_1;
    private ISelection fatherMethod_2;
    private ISelection individualMethod_1;
    private ISelection individualMethod_2;

    private ICrossover crossoverMethod;

    private IMutation mutation;

    private BaseCutCriteria cutCriteria;

    private BasePlayer player;

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

        this.player = ClassesFactory.givePlayer(conf.getIndividualClass(), conf.getHeight());

        List<BaseCutCriteria> criterias = new LinkedList<BaseCutCriteria>();

        criterias.add(conf.getAcceptableSolutionCriteria());
        criterias.add(conf.getContentCriteria());
        criterias.add(conf.getNumberOfGenerationsCriteria());
        criterias.add(conf.getTimeCriteria());
        criterias.add(conf.getStructureCriteria());

        this.cutCriteria = prepareCutCriteria(criterias);
    }

    private void prepareEquipment() {
        this.helmets = service.getData(Constants.Equipment.helmet);
        this.front = service.getData(Constants.Equipment.front);
        this.gloves = service.getData(Constants.Equipment.gloves);
        this.weapons = service.getData(Constants.Equipment.weapons);
        this.boots = service.getData(Constants.Equipment.boots);
    }

    private BaseCutCriteria prepareCutCriteria(List<BaseCutCriteria> criterias) {
        return criterias.stream().filter(x -> x.isInUse()).findFirst().orElse(null);
    }
}
