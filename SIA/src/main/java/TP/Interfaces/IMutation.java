package TP.Interfaces;

import TP.Models.Genetics.Chromosome;

public interface IMutation {

    /**
     * Método que se aplica sobre un cromosoma para
     * @param c cromosoma a mutar
     * @return void
     */
    public void mutate(Chromosome c);

}
