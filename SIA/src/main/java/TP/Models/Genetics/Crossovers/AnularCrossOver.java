package TP.Models.Genetics.Crossovers;

import TP.Interfaces.ICrossover;
import TP.Models.Genetics.Chromosome;

import java.util.concurrent.ThreadLocalRandom;

public class AnularCrossOver implements ICrossover {

    @Override
    public Chromosome[] cross(Chromosome p1, Chromosome p2) {
        //get random int between [0, S - 1], L between [0, ceil(S / 2)]
        int r = ThreadLocalRandom.current().nextInt(0, p1.getS());
        int l = ThreadLocalRandom.current().nextInt(0, (int) Math.ceil( (float) p1.getS() / 2));
        return cross(p1, p2, r, l);
    }

    public Chromosome[] cross(Chromosome p1, Chromosome p2, int r, int l) {
        if(p1 == null || p2 == null || l < 0) return null;
        //create two new Chromosomes
        Chromosome[] children = new Chromosome[] {new Chromosome(p1), new Chromosome(p2)};
        //copy l genes from p, use modulus
        for(int i = r; i < l; i++){
            children[0].getChromosome()[i % p1.getS()] = p2.getChromosome()[i % p1.getS()];
            children[1].getChromosome()[i % p1.getS()] = p1.getChromosome()[i % p1.getS()];
        }
        return children;
    }
}
