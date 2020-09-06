package TP.Models.Genetics.Mutations;

import TP.Models.Genetics.Chromosome;

import java.util.concurrent.ThreadLocalRandom;

public class GenMutation extends Mutation {

    public GenMutation(double mutationProbability) {
        super(mutationProbability);
    }

    @Override
    public Chromosome mutate(Chromosome c) {
        //revisar si se debe mutar
        if(ThreadLocalRandom.current().nextDouble() < getMutationProbability()) return c;
        //se debe mutar
        Chromosome ret = new Chromosome(c);
        //elegir un gen al azar
        int r = ThreadLocalRandom.current().nextInt(0, Chromosome.S);
        //el gen de altura puede ir desde 130 hasta 200 cm
        //los genes de items pueden ir desde 0 hasta 1.000.000
        int upperBound = 1000000;
        int lowerBound = 0;
        //si vamos a tocar el gen de altura, modificar los límites
        if(r == 0){
            upperBound = 200;
            lowerBound = 130;
        }
        //cambiarlo por otro valor entre
        ret.getChromosome()[r] = ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
        return ret;
    }
}
