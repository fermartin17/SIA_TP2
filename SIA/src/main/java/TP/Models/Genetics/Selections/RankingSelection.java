package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.*;

public class RankingSelection extends RouletteSelection{


    public RankingSelection(int K, double percentage) {
        super(K, percentage);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        population.sort(Comparator.comparing(BasePlayer::getPerformance));
        Collections.reverse(population);

        int totalCount = population.size();
        List<Double> relatives = new ArrayList<Double>();
        for(int i = 0; i < population.size(); i++) {
            relatives.add((double) ((totalCount - i)/Math.pow(totalCount,2)));
            i++;
        }
        return super.makeSelection(population,relatives);
    }
}
