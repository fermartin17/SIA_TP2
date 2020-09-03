package TP.Models;

import TP.Interfaces.ICrossover;
import TP.Interfaces.IFillMethod;
import TP.Models.Genetics.Selections.Selection;
import TP.Models.Player.BasePlayer;

import java.util.List;

public class FillAll implements IFillMethod {

    private ICrossover crossover;

    public FillAll(ICrossover crossover){
        this.crossover = crossover;
    }

    @Override
    public List<BasePlayer> fillParent(List<BasePlayer> parents, Selection parentSel, Selection offspringSel) {
        //elegir K padres
        List<BasePlayer> selectedParents = parentSel.makeSelection(parents);
        //cruzar a los padres elegidos
        List<BasePlayer> offspring = Generation.breed(parents, crossover);
        //añadir todos a la misma bolsa
        selectedParents.addAll(offspring);
        //hacer selección con métodos 3 y 4
        return offspringSel.makeSelection(selectedParents);
    }
}
