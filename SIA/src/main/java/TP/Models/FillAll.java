package TP.Models;

import TP.Interfaces.IFillMethod;

import java.util.Collections;
import java.util.List;

public class FillAll implements IFillMethod {

    private int size;

    public FillAll(int size){
        this.size = size;
    }

    @Override
    public List<BasePlayer> fillParent(List<BasePlayer> parents, List<BasePlayer> offspring,
                                       Selection parentSel, Selection offspringSel) {
        //añadir todos a la misma bolsa
        parents.addAll(offspring);
        Collections.shuffle(parents);
        //elegir con métodos 3 y 4
        return parents;
    }
}
