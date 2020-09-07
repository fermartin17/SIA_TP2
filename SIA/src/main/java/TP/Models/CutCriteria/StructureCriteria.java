package TP.Models.CutCriteria;

import TP.Models.Player.BasePlayer;
import TP.Models.Generation;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class StructureCriteria extends BaseCutCriteria {

    private List<Generation> generations;
    private double percentage;
    private int generationsWindow;

    public StructureCriteria(){}

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
