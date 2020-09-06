package TP.Models.Genetics.Crossovers;

import TP.Interfaces.ICrossover;
import TP.Models.Genetics.Chromosome;

import java.util.concurrent.ThreadLocalRandom;

public class OnePointCrossOver implements ICrossover {

    @Override
    public Chromosome[] cross(Chromosome p1, Chromosome p2) {
        //call function with random int between [0, S]
        return cross(p1, p2, ThreadLocalRandom.current().nextInt(0, p1.getS() + 1));
    }

    //método para facilitar el testeo
    public Chromosome[] cross(Chromosome p1, Chromosome p2, int n) {
        if(p1 == null || p2 == null) return null;
        //create two new Chromosomes
        Chromosome[] children = new Chromosome[] {new Chromosome(), new Chromosome()};
        //copy child 1 from parent 1, child 2 from parent 2 until r
        System.arraycopy(p1.getChromosome(), 0, children[0].getChromosome(), 0, n);
        System.arraycopy(p2.getChromosome(), 0, children[1].getChromosome(), 0, n);
        //swap parents and keep copying from r to the remaining (s - r)
        System.arraycopy(p2.getChromosome(), n, children[0].getChromosome(), n, p1.getS() - n);
        System.arraycopy(p1.getChromosome(), n, children[1].getChromosome(), n, p1.getS() - n);
        return children;
    }
}
