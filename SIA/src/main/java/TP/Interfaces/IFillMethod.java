package TP.Interfaces;

import TP.Models.Genetics.Selections.CombinedSelection;
import TP.Models.Player.BasePlayer;
import TP.Models.Genetics.Selections.Selection;
import TP.Services.RedisService;

import java.util.List;

public interface IFillMethod {

    List<BasePlayer> fill(List<BasePlayer> parents,
                                CombinedSelection parentSel,
                                CombinedSelection offspringSel, IService service);
}
