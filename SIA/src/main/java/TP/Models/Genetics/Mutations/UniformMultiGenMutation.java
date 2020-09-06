package TP.Models.Genetics.Mutations;

import TP.Models.Genetics.Chromosome;

import java.util.concurrent.ThreadLocalRandom;

public class UniformMultiGenMutation extends LimitedMultiGenMutation {

    public UniformMultiGenMutation(double mutationProbability) {
        super(mutationProbability);
    }

    @Override
    public Chromosome mutate(Chromosome c) {
        //loopeamos cada gen del cromosomas para ver si se muta o no
        Chromosome aux = new Chromosome(c);
        for(int i = 0; i < c.getS(); i++){
            if(ThreadLocalRandom.current().nextDouble() >= getMutationProbability()){
                aux = mutateCandidates(aux, new Integer[]{i});
            }
        }
        return aux;
    }
}
