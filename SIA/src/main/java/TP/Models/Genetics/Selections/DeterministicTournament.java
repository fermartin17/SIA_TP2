package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DeterministicTournament extends Selection {

    private int M;

    public DeterministicTournament(int K, double percentage, int M){
        super(K, percentage);
        this.M = M;
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        //lista a retornar
        List<BasePlayer> ret = new ArrayList<>(this.getK());
        //lista auxiliar de M individuos de la población
        List<BasePlayer> aux = new ArrayList<>(M);
        for(int i = 0; i < this.getK(); i++) {
            List<Integer> candidatesId = new ArrayList<>(M);
            //generar M índices aleatorios
            for (int j = 0; j < M; j++) {
                candidatesId.add(ThreadLocalRandom.current().nextInt(0, population.size()));
            }
            //agregar a cada individuo elegido a la lista auxiliar
            candidatesId.forEach(c -> aux.add(population.get(c)));
            //agregar al mejor a la lista de retorno
            //noinspection OptionalGetWithoutIsPresent
            ret.add(aux.stream().max(Comparator.comparing(BasePlayer::getPerformance)).get());
            //limpar la lista auxiliar
            aux.clear();
        }
        return ret;
    }

}
