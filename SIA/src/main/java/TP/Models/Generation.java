package TP.Models;

import TP.Interfaces.ICrossover;
import TP.Models.Genetics.Chromosome;
import TP.Models.Player.BasePlayer;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Generation {

    private List<BasePlayer> currentPopulation;
    private double bestHistoricFitness;
    private double bestFitness;
    private double currentFitness;
    private int generationNumber;

    public Generation(){
        this.currentPopulation = new LinkedList<>();
        this.bestHistoricFitness = this.bestFitness = this.currentFitness = 0;
        this.generationNumber = 0;
    }

    public static List<BasePlayer> breed(List<BasePlayer> selectedParents, ICrossover crossover){
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
            try {
                assert playerConstructor != null;
                child1 = (BasePlayer) playerConstructor.newInstance(childrenChromosomes[0]);
                child2 = (BasePlayer) playerConstructor.newInstance(childrenChromosomes[1]);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if(child1 != null) offspring.add(child1);
            if(child2 != null) offspring.add(child2);
        }
        return offspring;
    }

    public void nextGeneration(List<BasePlayer> newPopulation){
        this.currentPopulation = new ArrayList<>(newPopulation);
        this.generationNumber++;
        this.currentFitness = 0;
        this.bestFitness = 0;
    }
}
