package TP.Models.Genetics.Selections;

import TP.Interfaces.ISelection;
import TP.Models.BasePlayer;
import TP.Models.BaseSelection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProbabilisticTournament extends BaseSelection implements ISelection {

    private static int K = 100;
    private static double threshold = 0.75;

    public ProbabilisticTournament(double percentage){
        setPercentage(percentage);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        //lista a retornar
        List<BasePlayer> ret = new ArrayList<>(K);
        //lista auxiliar de M individuos de la población
        BasePlayer[] aux = new BasePlayer[2];
        for(int i = 0; i < K; i++) {
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
