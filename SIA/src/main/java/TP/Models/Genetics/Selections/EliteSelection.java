package TP.Models.Genetics.Selections;

import TP.Interfaces.ISelection;
import TP.Models.BasePlayer;
import TP.Models.BaseSelection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EliteSelection extends BaseSelection implements ISelection {

    private static int K = 100; //TODO: agarrar del archivo de configuración

    public EliteSelection(double probability){
        setProbability(probability);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        //sort by performance
        population.sort(Comparator.comparing(BasePlayer::calculatePerformance));
        //return best K, loop if K > size of population
        return IntStream.range(0, K)
                .mapToObj(i -> population.get(i % population.size()))
                .collect(Collectors.toCollection(() -> new ArrayList<>(K)));
    }
}
