package TP.Models;

import TP.Constants.Constants;
import TP.Helpers.Factories.ClassesFactory;
import TP.Interfaces.ICrossover;
import TP.Interfaces.IFillMethod;
import TP.Interfaces.IService;
import TP.Models.Genetics.Chromosome;
import TP.Models.Genetics.Selections.CombinedSelection;
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
    private BasePlayer bestFitness;
    private double currentFitness;
    private int generationNumber;
    public double lastGenerationPerformance;
    private IService redisService;
    private IFillMethod fillMethod;
    private CombinedSelection parentSelection;
    private CombinedSelection replacementSelection;


    //constructor vacío para los tests
    public Generation(){
        this.currentPopulation = new ArrayList<>();
        this.bestFitness = null;
        this.generationNumber = 0;
        this.lastGenerationPerformance = 0;
        this.fillMethod = null;
        this.redisService = null;
        this.parentSelection = null;
        this.replacementSelection = null;
    }


    public Generation(List<BasePlayer> currentPopulation, IFillMethod fillMethod, IService redisService,
                      CombinedSelection parentSelection, CombinedSelection replacementSelection){
        this.currentPopulation = currentPopulation;
        this.bestFitness = null;
        this.generationNumber = 0;
        this.lastGenerationPerformance = 0;
        this.fillMethod = fillMethod;
        this.redisService = redisService;
        this.parentSelection = parentSelection;
        this.replacementSelection = replacementSelection;
    }

    public static List<BasePlayer> breed(List<BasePlayer> selectedParents, ICrossover crossover, RedisService service){
        List<BasePlayer> offspring = new ArrayList<>(selectedParents.size());
        //mezclar a la lista de padres seleccionados
        Collections.shuffle(selectedParents);
        Chromosome[] childrenChromosomes;
        //agarramos el tipo de player y su constructor
        for(int i = 0; i < selectedParents.size() - 1; i++){
            childrenChromosomes = crossover.cross(
                                selectedParents.get(i).getChromosome(),
                                selectedParents.get(i+1).getChromosome());
            offspring.add(generatePlayer(childrenChromosomes[0],service, selectedParents.get(0)));
            offspring.add(generatePlayer(childrenChromosomes[1],service, selectedParents.get(0)));
        }
        return offspring;
    }

    public void nextGeneration(){
        compareBestFitness();
        System.out.println("Generation Best Fitness: " + bestFitness.calculatePerformance());
        this.currentPopulation = fillMethod.fill(currentPopulation, parentSelection,
                                replacementSelection, redisService);
        this.generationNumber++;
    }

    private void compareBestFitness(){
        BasePlayer aux = currentPopulation.stream().max(Comparator.comparing(BasePlayer::calculatePerformance)).get();
        System.out.println(aux.getPerformance());
        this.lastGenerationPerformance = aux.getPerformance();
        this.bestFitness =  bestFitness == null? aux : this.bestFitness.comparePerformance(aux);
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
