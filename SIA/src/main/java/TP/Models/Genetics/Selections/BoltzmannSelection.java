package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.List;
import java.util.stream.Collectors;

public class BoltzmannSelection extends RouletteSelection {

    private long generationNumber;
    private final double t0;
    private final double tC;

    public BoltzmannSelection(int K, double percentage,double t0, double tC) {
        super(K, percentage);
        this.generationNumber = 0;
        this.t0 = t0;
        this.tC = tC;
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        double total;
        double t = getTemperature();


        total = (population.stream().mapToDouble(BasePlayer::getPerformance).sum()) / t;

        List<Double> relatives = (List<Double>) population.stream()
                .map(x -> x.getPerformance() / total)
                .collect(Collectors.toList());


        this.generationNumber++;
        return super.makeSelection(population, relatives);


    }

    private double getTemperature() {
        return (this.tC + (this.t0 - tC) * Math.exp(-super.getK() * generationNumber));
    }

}
