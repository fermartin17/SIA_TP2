package TP.Interfaces;

import TP.Models.Player.BasePlayer;
import TP.Models.Genetics.Selections.Selection;

import java.util.List;

public interface IFillMethod {

    List<BasePlayer> fillParent(List<BasePlayer> parents, Selection parentSel, Selection offspringSel);
}
