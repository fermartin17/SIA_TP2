package TP.Models.Genetics;

import TP.Interfaces.ICrossover;

import java.util.concurrent.ThreadLocalRandom;

public class OnePointCrossOver implements ICrossover {

    @Override
    public Chromosome[] cross(Chromosome p1, Chromosome p2) {
        //get random int between [0, S]
        int r = ThreadLocalRandom.current().nextInt(0, Chromosome.S + 1);
        //create two new Chromosomes
        Chromosome[] children = new Chromosome[] {new Chromosome(), new Chromosome()};
        //copy child 1 from parent 1, child 2 from parent 2 until r
        System.arraycopy(p1.getChromosome(), 0, children[0].getChromosome(), 0, r);
        System.arraycopy(p2.getChromosome(), 0, children[1].getChromosome(), 0, r);
        //swap parents and keep copying from r to the remaining (s - r)
        System.arraycopy(p2.getChromosome(), r, children[0].getChromosome(), r, Chromosome.S - r);
        System.arraycopy(p1.getChromosome(), r, children[1].getChromosome(), r, Chromosome.S - r);
        return children;
    }
}
