package TP.Models.Genetics.Selections;

import TP.Interfaces.ISelection;
import TP.Models.BasePlayer;
import TP.Models.BaseSelection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DeterministicTournament extends BaseSelection implements ISelection{

    private static int M = 10;
    private static int K = 100;

    public DeterministicTournament(double percentage){
        this.setPercentage(percentage);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        //lista a retornar
        List<BasePlayer> ret = new ArrayList<>(K);
        //lista auxiliar de M individuos de la población
        List<BasePlayer> aux = new ArrayList<>(M);
        for(int i = 0; i < K; i++) {
            List<Integer> candidatesId = new ArrayList<>(M);
            //generar M índices aleatorios
            for (int j = 0; j < M; j++) {
                candidatesId.add(ThreadLocalRandom.current().nextInt(0, population.size()));
            }
            //agregar a cada individuo elegido a la lista auxiliar
            candidatesId.stream().map(c -> aux.add(population.get(c)));
            //agregar al mejor a la lista de retorno
            ret.add(aux.stream().max(Comparator.comparing(BasePlayer::calculatePerformance)).get());
        }
        return ret;
    }

}
