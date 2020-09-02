package TP.Models.CutCriteria;

import TP.Interfaces.ICutCriteria;
import TP.Models.BasePlayer;
import TP.Models.Generation;

import java.util.LinkedList;
import java.util.List;

public class StructureCriteria implements ICutCriteria {

    private List<Generation> generations;
    private final double percentage;
    private final int generationsWindow;

    public StructureCriteria(double percentage, int generationWindow){
        this.generations = new LinkedList<>();
        this.percentage = percentage;
        this.generationsWindow = generationWindow;
    }

    @Override
    public boolean cutProgram(Generation g) {
        if(generations.size() < generationsWindow) return false;
        if (calculateGenerationChange(g) > percentage) return true;
        generations.add(g);
        return false;
    }

    private double calculateGenerationChange(Generation g){
        int survivors = 0;
        //agarramos las últimas n generaciones
        int genSize = generations.size();
        List<Generation> lastGenerations = generations.subList(genSize- generationsWindow, genSize);
        //por cada individuo, si estuvo en las últimas n generaciones, sobrevivió
        for(BasePlayer individual : g.getCurrentPopulation()){
            boolean survived = lastGenerations
                    .stream()
                    .allMatch(gen -> g.getCurrentPopulation().contains(individual));
            if(survived) survivors++;
        }
        return (double)survivors / g.getCurrentPopulation().size();
    }
}
