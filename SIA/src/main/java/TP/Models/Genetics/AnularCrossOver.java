package TP.Models.Genetics;

import TP.Interfaces.ICrossover;

import java.util.concurrent.ThreadLocalRandom;

public class AnularCrossOver implements ICrossover {

    @Override
    public Chromosome[] cross(Chromosome p1, Chromosome p2) {
        //get random int between [0, S - 1], L between [0, ceil(S / 2)]
        int r = ThreadLocalRandom.current().nextInt(0, Chromosome.S);
        int l = ThreadLocalRandom.current().nextInt(0, (int) Math.ceil( (float) Chromosome.S / 2));
        //create two new Chromosomes
        Chromosome[] children = new Chromosome[] {new Chromosome(p1), new Chromosome(p2)};
        //copy l genes from p, use modulus
        for(int i = r; i < l; i++){
            children[0].getChromosome()[i % Chromosome.S] = p2.getChromosome()[i % Chromosome.S];
            children[1].getChromosome()[i % Chromosome.S] = p1.getChromosome()[i % Chromosome.S];
        }
        return children;
    }
}
