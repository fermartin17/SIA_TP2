package TP.Interfaces;

import TP.Models.Genetics.Chromosome;

public interface ICrossover {

    Chromosome[] cross(Chromosome p1, Chromosome p2);
}
