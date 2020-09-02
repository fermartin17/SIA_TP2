package TP.Models.Genetics.Mutations;

import TP.Models.Genetics.Chromosome;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class CompleteMultiGenMutation extends LimitedMultiGenMutation {

    public CompleteMultiGenMutation(double mutationProbability) {
        super(mutationProbability);
        super.setM(Chromosome.S); //lo seteo pero no es necesario
    }

    @Override
    public Chromosome mutate(Chromosome c) {
        //revisar si se debe mutar
        if(ThreadLocalRandom.current().nextDouble() < getMutationProbability()) return c;
        //se debe mutar
        //generamos una lista de 0 hasta S y se la pasamos para que mute todos los genes
        return mutateCandidates(c, (Integer[]) Collections
                .singletonList(IntStream.range(0, Chromosome.S + 1)).toArray());
    }
}
