package TP.Models.Genetics;

import TP.Interfaces.IMutation;

import java.util.concurrent.ThreadLocalRandom;

public class MultiGenLimitedMutation implements IMutation {

    private static int M = 4;

    @Override
    public Chromosome mutate(Chromosome c) {
        Chromosome ret = new Chromosome(c);
        //elegir una cantidad de genes entre 0 y M TODO: definir m en archivo de configuración
        int nOfMutations = ThreadLocalRandom.current().nextInt(0, M +1);
        Integer[] candidatesToMutation = new Integer[nOfMutations];
        //definir los genes candidatos
        for(int i = 0; i < candidatesToMutation.length; i++){
            candidatesToMutation[i] = ThreadLocalRandom.current().nextInt(0, Chromosome.S +1);
        }
        //el gen de altura puede ir desde 130 hasta 200 cm
        //los genes de items pueden ir desde 0 hasta 1.000.000
        int upperBound, lowerBound;
        for(Integer i : candidatesToMutation){
            //si vamos a tocar el gen de altura, modificar los límites
            if(i == 0){
                upperBound = 200;
                lowerBound = 130;
            }else{
                upperBound = 1000000;
                lowerBound = 0;
            }
            //cambiarlo por otro valor entre
            ret.getChromosome()[i] = ThreadLocalRandom.current().nextInt(lowerBound, upperBound +1);
        }
        return ret;
    }
}
