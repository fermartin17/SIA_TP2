package TP.Interfaces;

import TP.Models.Generation;

public interface ICutCriteria {

    /**
     * Método para verificar si se debe cortar la ejecución del programa
     * @param g Generación actual
     * @return boolean indicando true si se debe cortar, false si se debe continuar
     */
    public boolean cutProgram(Generation g);
}
