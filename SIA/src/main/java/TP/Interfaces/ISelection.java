package TP.Interfaces;

import TP.Models.BasePlayer;

import java.util.List;

public interface ISelection {

    /**
     * Método para crear una selección de la población total
     * @param population Población sobre la cual se va a aplicar la selección
     * @return Lista conteniendo a individuos de la población original
     */
    List<BasePlayer> makeSelection(List<BasePlayer> population);
}
