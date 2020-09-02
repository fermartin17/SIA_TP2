package TP.Interfaces;

import TP.Models.Genetics.Chromosome;

public interface IMutation {

    /**
     * Método que se aplica una mutación sobre un cromosoma
     * @param c cromosoma a mutar
     * @return nuevo cromosoma mutado
     */
    public Chromosome mutate(Chromosome c);

}
