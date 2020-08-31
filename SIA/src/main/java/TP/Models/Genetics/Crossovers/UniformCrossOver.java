package TP.Models.Genetics.Crossovers;

import TP.Interfaces.ICrossover;
import TP.Models.Genetics.Chromosome;

import java.util.concurrent.ThreadLocalRandom;

public class UniformCrossOver implements ICrossover {

    @Override
    public Chromosome[] cross(Chromosome p1, Chromosome p2) {
        if(p1 == null || p2 == null) return null;
        Boolean[] randoms = new Boolean[Chromosome.S];
        for(int i = 0; i < randoms.length; i++) randoms[i] = ThreadLocalRandom.current().nextBoolean();
        return cross(p1, p2, randoms);
    }


    public Chromosome[] cross(Chromosome p1, Chromosome p2, Boolean[] randoms) {
        if(p1 == null || p2 == null || randoms == null) return null;
        //create two new Chromosomes
        Chromosome[] children = new Chromosome[] {new Chromosome(), new Chromosome()};
        //child0 always copies from aux1, child1 always copies from aux2
        //if random is true, aux1 = p1, aux2 = p2; else, swap aux1 and aux2
        for(int i = 0; i < Chromosome.S; i++){
            Chromosome aux1, aux2;
            if(randoms[i]){
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
