package TP.Models;

import TP.Interfaces.IFillMethod;
import TP.Models.Genetics.Selections.Selection;
import TP.Models.Player.BasePlayer;

import java.util.List;

public class FillParent implements IFillMethod {

    @Override
    public List<BasePlayer> fillParent(List<BasePlayer> parents,
                                       Selection parentSel, Selection offspringSel) {
        return null;
    }
}
