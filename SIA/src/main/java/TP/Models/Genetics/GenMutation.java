package TP.Models.Genetics;

import TP.Interfaces.IMutation;

import java.util.concurrent.ThreadLocalRandom;

public class GenMutation implements IMutation {

    @Override
    public Chromosome mutate(Chromosome c) {
        Chromosome ret = new Chromosome(c);
        //elegir un gen al azar
        int r = ThreadLocalRandom.current().nextInt(0, Chromosome.S +1);
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
        ret.getChromosome()[r] = ThreadLocalRandom.current().nextInt(lowerBound, upperBound +1);
        return ret;
    }
}
