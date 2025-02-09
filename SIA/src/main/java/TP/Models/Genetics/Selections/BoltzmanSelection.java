package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.List;
import java.util.stream.Collectors;

public class BoltzmanSelection extends RouletteSelection {

    private long generationNumber;
    private final double t0;
    private final double tC;
    private final double boltzmanConstant;

    public BoltzmanSelection(int K, double percentage, double t0, double tC) {
        super(K, percentage);
        this.generationNumber = 0;
        this.t0 = t0;
        this.tC = tC;
        this.boltzmanConstant = 1.38064852 * Math.pow(10,-23);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        double t = getTemperature();
        double total = (population.stream().mapToDouble(i -> Math.exp(i.getPerformance())/t)).average().getAsDouble();

        List<Double> relatives = (List<Double>) population.stream()
                .map(x -> ((Math.exp(x.getPerformance())/t) / total)/population.size())
                .collect(Collectors.toList());

        this.generationNumber++;
        return super.makeSelection(population, relatives);
    }

    private double getTemperature() {
        return (this.tC + (this.t0 - tC) * Math.exp(-boltzmanConstant * generationNumber));
    }
}
