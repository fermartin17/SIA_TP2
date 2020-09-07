package TP.Models.CutCriteria;

import TP.Models.Generation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseCutCriteria {

    private boolean inUse;

    /**
     * Método para verificar si se debe cortar la ejecución del programa
     * @param g Generación actual
     * @return boolean indicando true si se debe cortar, false si se debe continuar
     */
    public abstract boolean cutProgram(Generation g);
}
