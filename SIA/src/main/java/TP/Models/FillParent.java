package TP.Models;

import TP.Interfaces.IFillMethod;

import java.util.List;

public class FillParent implements IFillMethod {

    @Override
    public List<BasePlayer> fillParent(List<BasePlayer> parents, List<BasePlayer> offspring,
                                       Selection parentSel, Selection offspringSel) {
        return null;
    }
}
