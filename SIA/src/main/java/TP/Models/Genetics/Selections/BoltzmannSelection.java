package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.List;
import java.util.stream.Collectors;

public class BoltzmannSelection extends RouletteSelection {

    private long generationNumber;

    public BoltzmannSelection(int K, double percentage) {
        super(K, percentage);
        this.generationNumber = 0;
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        double total;
        double t = getTemperature(0,0,super.getK(),generationNumber);


        total = (population.stream().mapToDouble(BasePlayer::getPerformance).sum()) / t;

        List<Double> relatives = (List<Double>) population.stream()
                .map(x -> x.getPerformance() / total)
                .collect(Collectors.toList());


        this.generationNumber++;
        return super.makeSelection(population, relatives);


    }

    private static double getTemperature(double T0, double Tc, double K, long generationNumber) {
        return (Tc + (T0 - Tc) * Math.exp(-K * generationNumber));
    }

}
