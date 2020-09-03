package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingSelection extends RouletteSelection{


    public RankingSelection(int K, double percentage) {
        super(K, percentage);
    }
//
//    @Override
//    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
//        population.sort(Comparator.comparing(BasePlayer:: getPerformance));
//    }
}
