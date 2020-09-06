package TP.Models.Genetics.Mutations;

import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

public class LimitedMultiGenMutation extends Mutation {

    @Getter
    @Setter
    private int M = 4;

    public LimitedMultiGenMutation(double mutationProbability) {
        super(mutationProbability);
    }

    public LimitedMultiGenMutation(double mutationProbability, int M){
        super(mutationProbability);
        this.M = M;
    }

    @Override
    public Chromosome mutate(Chromosome c) {
        //revisar si se debe mutar
        if(ThreadLocalRandom.current().nextDouble() < getMutationProbability()) return c;
        //se debe mutar
        Chromosome ret = new Chromosome(c);
        //elegir una cantidad de genes entre 0 y M
        int nOfMutations = ThreadLocalRandom.current().nextInt(0, M +1);
        Integer[] candidatesToMutation = new Integer[nOfMutations];
        //definir los genes candidatos
        for(int i = 0; i < candidatesToMutation.length; i++){
            candidatesToMutation[i] = ThreadLocalRandom.current().nextInt(0, c.getS() +1);
        }
        return mutateCandidates(ret, candidatesToMutation);
    }

    public Chromosome mutateCandidates(Chromosome chromosome, Integer[] candidatesToMutation){
        //el gen de altura puede ir desde 130 hasta 200 cm
        //los genes de items pueden ir desde 0 hasta 1.000.000
        int upperBound, lowerBound;
        for(Integer i : candidatesToMutation){
            //si vamos a tocar el gen de altura, modificar los límites
            if(i == 0){
                upperBound = chromosome.getChromosome()[0] + 20;
                if(upperBound > 200) upperBound = 200;
                lowerBound = chromosome.getChromosome()[0] - 20;
                if(lowerBound < 130) lowerBound = 130;
            }else{
                upperBound = 1000000;
                lowerBound = 0;
            }
            //cambiarlo por otro valor entre
            chromosome.getChromosome()[i] = ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
        }
        return chromosome;
    }
}
