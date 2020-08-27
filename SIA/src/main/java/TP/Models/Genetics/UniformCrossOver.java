package TP.Models.Genetics;

import TP.Interfaces.ICrossover;

import java.util.concurrent.ThreadLocalRandom;

public class UniformCrossOver implements ICrossover {

    @Override
    public Chromosome[] cross(Chromosome p1, Chromosome p2) {
        //create two new Chromosomes
        Chromosome[] children = new Chromosome[] {new Chromosome(), new Chromosome()};
        //child0 always copies from aux1, child1 always copies from aux2
        //if random is true, aux1 = p1, aux2 = p2; else, swap aux1 and aux2
        for(int i = 0; i < Chromosome.S; i++){
            Chromosome aux1, aux2;
            if(ThreadLocalRandom.current().nextBoolean()){
                aux1 = p1;
                aux2 = p2;
            }else{
                aux1 = p2;
                aux2 = p1;
            }
            children[0].getChromosome()[i] = aux1.getChromosome()[i];
            children[1].getChromosome()[i] = aux2.getChromosome()[i];
        }
        return children;
    }
}
