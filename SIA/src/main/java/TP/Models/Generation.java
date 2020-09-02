package TP.Models;

import lombok.Getter;
import lombok.Setter;

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
}
