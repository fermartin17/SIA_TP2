package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EliteSelection extends Selection {

    public EliteSelection(int K, double percentage){
        super(K, percentage);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        List<BasePlayer> copy = new ArrayList<>(population);
        //sort by performance
        copy.sort(Comparator.comparing(BasePlayer::getPerformance));
        Collections.reverse(copy);
        //return best K, loop if K > size of population
        List<BasePlayer> ret = new ArrayList<>(this.getK());
        for(int i = 0; i < this.getK(); i++){
            ret.add(copy.get(i % copy.size()));
        }
        return ret;
    }
}
