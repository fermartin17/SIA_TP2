package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
        double totalSum = population.stream().mapToDouble(BasePlayer::getPerformance).sum();
        List<Double> relatives = calculateRelativePerformance(population, totalSum);
        List<Double> accumulated = calculateAccumulatedList(relatives);
        List<Double> randoms = generateRandoms();
        return selectPopulation(accumulated, randoms, population);
    }

    //calcular las aptitudes relativas
    public List<Double> calculateRelativePerformance(List<BasePlayer> population, double totalSum){
        return  population
                .stream()
                .map(p -> p.getPerformance() / totalSum)
                .collect(Collectors.toCollection(() -> new ArrayList<>(this.getK())));
    }

    //calcular las aptitudes relativas acumuladas
    public List<Double> calculateAccumulatedList(List<Double> relatives){
        List<Double> acum = new ArrayList<>(relatives.size());
        acum.add(relatives.get(0));
        for(int i= 1; i < relatives.size() ; i++){
            acum.add(acum.get(i-1) + relatives.get(i));
        }
        return acum;
    }

    public List<Double> generateRandoms(){
        List<Double> ret = new ArrayList<>(this.getK());
        for(int i = 0; i < this.getK(); i++) ret.add(ThreadLocalRandom.current().nextDouble());
        return ret;
    }

    public List<BasePlayer> selectPopulation(List<Double> accumulated,
                                              List<Double> randoms, List<BasePlayer> population){
        List<BasePlayer> ret = new ArrayList<>(getK());
        boolean found;
        for(int i = 0; i < getK(); i++){
            double r = randoms.get(i);
            found = false;
            for (int j = 0; !found && j < accumulated.size(); j++) {
                if(r <= accumulated.get(j)){
                    ret.add(population.get(j));
                    found = true;
                }
            }
        }
        return ret;
    }

}
