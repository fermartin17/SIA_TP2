package TP.Models;

import TP.Interfaces.ICrossover;
import TP.Interfaces.IFillMethod;
import TP.Models.Genetics.Selections.CombinedSelection;
import TP.Models.Genetics.Selections.Selection;
import TP.Models.Player.BasePlayer;

import java.util.List;

public class FillAll implements IFillMethod {

    private final ICrossover crossover;

    public FillAll(ICrossover crossover){
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
        //añadir a todos los hijos a la población actual
        parents.addAll(offspring);
        //agarrar N individuos haciendo la selección con métodos 3 y 4
        return replaceSel.makeSelection(selectedParents);
    }
}
