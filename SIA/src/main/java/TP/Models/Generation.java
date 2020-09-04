package TP.Models;

import TP.Constants.Constants;
import TP.Helpers.Factories.ClassesFactory;
import TP.Interfaces.ICrossover;
import TP.Interfaces.IService;
import TP.Models.Genetics.Chromosome;
import TP.Models.Player.BasePlayer;
import TP.Services.RedisService;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Generation {

    private List<BasePlayer> currentPopulation;
    private double bestFitness;
    private double currentFitness;
    private int generationNumber;
    public double lastGenerationPerformance;
    private IService redisService;

    public Generation(){
        this.currentPopulation = new LinkedList<>();
        this.bestFitness = this.currentFitness = 0;
        this.generationNumber = 0;
        this.lastGenerationPerformance = 0;
    }

    public Generation(List<BasePlayer> currentPopulation){
        this.currentPopulation = currentPopulation;
        this.bestFitness = null;
        this.generationNumber = 0;
        this.lastGenerationPerformance = 0;
    }

    public static List<BasePlayer> breed(List<BasePlayer> selectedParents, ICrossover crossover, RedisService service){
        List<BasePlayer> offspring = new ArrayList<>(selectedParents.size());
        //mezclar a la lista de padres seleccionados
        Collections.shuffle(selectedParents);
        Chromosome[] childrenChromosomes;
        //agarramos el tipo de player y su constructor
        Class<?> playerClass = selectedParents.get(0).getClass();
        Constructor<?> playerConstructor = null;
        //agarramos el constructor de esa clase
        try {
            playerConstructor = playerClass.getConstructor(playerClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < selectedParents.size() - 1; i++){
            childrenChromosomes = crossover.cross(selectedParents.get(i).getChromosome(),
                            selectedParents.get(i+1).getChromosome());
            BasePlayer child1 = null;
            BasePlayer child2 = null;
            assert playerConstructor != null;
            child1 = generatePlayer(childrenChromosomes[0],service,selectedParents.get(0));
            child2 = generatePlayer(childrenChromosomes[1],service, selectedParents.get(0));
//                child1 = (BasePlayer) playerConstructor.newInstance(childrenChromosomes[0]);
//                child2 = (BasePlayer) playerConstructor.newInstance(childrenChromosomes[1]);

            if(child1 != null) offspring.add(child1);
            if(child2 != null) offspring.add(child2);
        }
        return offspring;
    }

    public void nextGeneration(List<BasePlayer> newPopulation){
        compareBestFitness(newPopulation);
        this.currentPopulation = new ArrayList<>(newPopulation);
        this.generationNumber++;
    }

    private void compareBestFitness(List<BasePlayer>  newPopulation){
        BasePlayer aux = newPopulation.stream().max(Comparator.comparing(BasePlayer::getPerformance)).get();
        System.out.println(aux.getPerformance());
        this.lastGenerationPerformance = aux.getPerformance();
        this.bestFitness = this.bestFitness.comparePerformance(aux);
    }

    private static BasePlayer generatePlayer(Chromosome chromosome, RedisService service, BasePlayer parent){
        BasePlayer aux = ClassesFactory.givePlayer(parent.getName());
        aux.setHeight(chromosome.getChromosome()[0]);
        aux.getEquipment().add(service.getWeapons().get(chromosome.getChromosome()[1]));
        aux.getEquipment().add(service.getBoots().get(chromosome.getChromosome()[2]));
        aux.getEquipment().add(service.getHelmets().get(chromosome.getChromosome()[3]));
        aux.getEquipment().add(service.getGloves().get(chromosome.getChromosome()[4]));
        aux.getEquipment().add(service.getFronts().get(chromosome.getChromosome()[5]));
        aux.CalculateAll();
        aux.setChromosome(chromosome);

        return aux;
    }
}
