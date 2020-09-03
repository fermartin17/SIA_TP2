package TP.Models.Genetics.Selections;

import TP.Interfaces.ISelection;
import TP.Models.Player.BasePlayer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public abstract class Selection implements ISelection {

    private double percentage;
    private int K;

    public Selection(int K, double percentage){
        this.K = K;
        this.percentage = percentage;
    }
}
