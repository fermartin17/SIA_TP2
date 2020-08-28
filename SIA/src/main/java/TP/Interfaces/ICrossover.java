package TP.Interfaces;

import TP.Models.Genetics.Chromosome;

public interface ICrossover {

    /**
     * Método para que, dados 2 cromosomas, genere 2 cromosomas que resultan de la cruza
     * @param p1 parent 1
     * @param p2 parent 2
     * @return array de 2 elementos de tipo Chromosome
     */
    Chromosome[] cross(Chromosome p1, Chromosome p2);

}
