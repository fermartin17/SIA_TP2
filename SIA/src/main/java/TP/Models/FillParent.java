package TP.Models;

import TP.Interfaces.ICrossover;
import TP.Interfaces.IFillMethod;
import TP.Models.Genetics.Selections.CombinedSelection;
import TP.Models.Genetics.Selections.Selection;
import TP.Models.Player.BasePlayer;

import java.util.List;

public class FillParent implements IFillMethod {

    private final ICrossover crossover;

    public FillParent(ICrossover crossover){
        this.crossover = crossover;
    }

    @Override
    public List<BasePlayer> fill(List<BasePlayer> parents,
                                       CombinedSelection parentSel,
                                       CombinedSelection replaceSel) {
        //elegir K padres a cruzar
        List<BasePlayer> selectedParents = parentSel.makeSelection(parents);
        //cruzar a los padres elegidos
        List<BasePlayer> offspring = Generation.breed(parents, crossover);
        //la diferencia entre la cantidad de hijos y la generación actual
        int generationDifference = parents.size() - offspring.size();
        //si K >= N, devolvemos N hijos
        if(generationDifference <= 0) return replaceSel.makeSelection(offspring);
        //sino, agarramos (K-N) padres
        //cambiamos la cantidad de individuos que vamos a agarrar
        replaceSel.changeK(generationDifference);
        //sumamos a la lista de hijos a los seleccionados de los padres
        offspring.addAll(replaceSel.makeSelection(parents));
        return offspring;
    }

}
