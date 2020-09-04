package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.List;
import java.util.stream.Collectors;

public class BoltzmannSelection extends Selection{

    public BoltzmannSelection(int K, double percentage) {
        super(K, percentage);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        double total = 0;
        double t = 0;

        total = (population.stream().mapToDouble(BasePlayer::getPerformance).sum())/t;

        List<Double> relatives = population.stream().mapToDouble(i -> i.getPerformance()).forEach(i -> i/total).collect(Collectors.toList());

    }


    public void aaaa(Player[] players, int k, double t){
        Player[] aux = new Player[k];
        double total = 0;
        double[] relative = new double[aux.length];
        double[] accumulated = new double[aux.length];

        for (Player p : players) {
            total+= Math.exp(p.performance()/t);
        }
        for (int i = 0; i < relative.length; i++) {
            relative[i] = Math.exp(players[i].performance()/t)/total;
        }
        accumulated[0] = relative[0];
        for (int i = 1; i < accumulated.length; i++) {
            accumulated[i] = relative[i] + accumulated[i-1];
        }
        for (int i = 0; i < k; i++) {
            double random = Math.random();
            for (int j = 1; j < accumulated.length; j++) {
                if (accumulated[j] > random) {
                    aux[i] = players[j-1];
                }
            }
        }
        return aux;
    }
}
