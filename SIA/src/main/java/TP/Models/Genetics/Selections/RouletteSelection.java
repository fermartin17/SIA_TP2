package TP.Models.Genetics.Selections;

import TP.Interfaces.ISelection;
import TP.Models.BasePlayer;
import TP.Models.BaseSelection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RouletteSelection extends BaseSelection implements ISelection {

    public static int K = 100; //TODO: agarrar del archivo de configuración

    public RouletteSelection(double probability){
        setProbability(probability);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        double totalSum = population.stream().mapToDouble(BasePlayer::getPerformance).sum();
        List<Double> relatives = calculateRelativePerformance(population, totalSum);
        List<Double> accumulated = calculateAccumulatedList(relatives);
        List<Double> randoms = generateRandoms();
        return selectPopulation(accumulated, randoms, population);
    }

    //calcular las aptitudes relativas
    private List<Double> calculateRelativePerformance(List<BasePlayer> population, double totalSum){
        return  population
                .stream()
                .map(p -> p.calculatePerformance() / totalSum)
                .collect(Collectors.toCollection(() -> new ArrayList<>(K)));
    }

    //calcular las aptitudes relativas acumuladas
    private List<Double> calculateAccumulatedList(List<Double> relatives){
        List<Double> acum = new ArrayList<>(K);
        acum.add(relatives.get(0));
        IntStream.range(1, K).forEach(i -> acum.add(acum.get(i - 1) + acum.get(i)));
        return acum;
    }

    public List<Double> generateRandoms(){
        List<Double> ret = new ArrayList<>(K);
        for(int i = 0; i < K; i++) ret.add(ThreadLocalRandom.current().nextDouble());
        return ret;
    }

    private List<BasePlayer> selectPopulation(List<Double> accumulated,
                                              List<Double> randoms, List<BasePlayer> population){
        List<BasePlayer> ret = new ArrayList<>(randoms.size());
        boolean found;
        for (double r : randoms) {
            found = false;
            for (int j = 0; !found && j < accumulated.size() - 1; j++) {
                if (accumulated.get(j) < r && r <= accumulated.get(j + 1)) {
                    ret.add(population.get(j + 1));
                    found = true;
                    break;
                }
            }
        }
        return ret;
    }

}
