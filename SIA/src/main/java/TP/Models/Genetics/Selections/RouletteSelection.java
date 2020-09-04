package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RouletteSelection extends Selection {

    public RouletteSelection(int K, double percentage){
        super(K, percentage);
    }


    public List<BasePlayer> makeSelection(List<BasePlayer> population, List<Double> relatives) {
        List<Double> randoms = generateRandoms();
        List<Double> accumulated = calculateAccumulatedList(relatives);
        return selectPopulation(accumulated, randoms, population);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        double totalSum = population.stream().mapToDouble(BasePlayer::calculatePerformance).sum();
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
                .collect(Collectors.toCollection(() -> new ArrayList<>(this.getK())));
    }

    //calcular las aptitudes relativas acumuladas
    private List<Double> calculateAccumulatedList(List<Double> relatives){
        List<Double> acum = new ArrayList<>(this.getK());
        acum.add(relatives.get(0));
        IntStream.range(1, this.getK()).forEach(i -> acum.add(acum.get(i - 1) + acum.get(i)));
        return acum;
    }

    public List<Double> generateRandoms(){
        List<Double> ret = new ArrayList<>(this.getK());
        for(int i = 0; i < this.getK(); i++) ret.add(ThreadLocalRandom.current().nextDouble());
        return ret;
    }

    private List<BasePlayer> selectPopulation(List<Double> accumulated,
                                              List<Double> randoms, List<BasePlayer> population){
        List<BasePlayer> ret = new ArrayList<>(randoms.size());
        boolean found;
        for (double r : randoms) {
            found = false;
            //noinspection UnclearExpression,ConstantConditions
            for (int j = 0; !found && j < accumulated.size() - 1; j++) {
                if (accumulated.get(j) < r && r <= accumulated.get(j + 1)) {
                    ret.add(population.get(j + 1));
                    //noinspection UnusedAssignment
                    found = true;
                    break;
                }
            }
        }
        return ret;
    }

}
