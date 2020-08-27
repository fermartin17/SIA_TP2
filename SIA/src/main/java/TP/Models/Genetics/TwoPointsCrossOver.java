package TP.Models.Genetics;

import TP.Interfaces.ICrossover;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class TwoPointsCrossOver implements ICrossover {

    @Override
    public Chromosome[] cross(Chromosome p1, Chromosome p2) {
        //get 2 random int between [0, S], r2 must be > r1 -> r2 [0, S], r1[0,r1]
        int r2 = ThreadLocalRandom.current().nextInt(0, Chromosome.S + 1);
        int r1 = ThreadLocalRandom.current().nextInt(0, r2 + 1);
        //create two new Chromosomes, child0 from p1, child1 from p2
        Chromosome[] children = new Chromosome[] {new Chromosome(p1), new Chromosome(p2)};
        //swap genes between r1 and r2
        System.arraycopy(p2.getChromosome(), r1, children[0].getChromosome(), r1, r2 - r1);
        System.arraycopy(p1.getChromosome(), r1, children[1].getChromosome(), r1, r2 - r1);
        return children;
    }
}
