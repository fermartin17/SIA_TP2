package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProbabilisticTournament extends Selection {

    private static double threshold = 0.75; //TODO: ver esto

    public ProbabilisticTournament(int K, double percentage){
        super(K, percentage);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        //lista a retornar
        List<BasePlayer> ret = new ArrayList<>(this.getK());
        //lista auxiliar de M individuos de la población
        BasePlayer[] aux = new BasePlayer[2];
        for(int i = 0; i < this.getK(); i++) {
            //elegir 2 individuos aleatorios
            aux[0] = population.get(ThreadLocalRandom.current().nextInt(0, population.size()));
            aux[1] = population.get(ThreadLocalRandom.current().nextInt(0, population.size()));
            //put in 0 most fit, 1 less fit
            if(aux[0].calculatePerformance().compareTo(aux[1].calculatePerformance()) <= 0){
                BasePlayer temp = aux[0];
                aux[0] = aux[1];
                aux[1] = temp;
            }
            //if above threshold, add most fit
            boolean aboveThreshold = ThreadLocalRandom.current().nextDouble() > threshold;
            if(aboveThreshold){
                ret.add(aux[0]);
            }else{
                ret.add(aux[1]);
            }
        }
        return ret;
    }
}
