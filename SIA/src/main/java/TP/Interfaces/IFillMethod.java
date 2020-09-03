package TP.Interfaces;

import TP.Models.BasePlayer;
import TP.Models.Selection;

import java.util.List;

public interface IFillMethod {

    List<BasePlayer> fillParent(List<BasePlayer> parents, List<BasePlayer> offspring,
                                       Selection parentSel, Selection offspringSel);
}
