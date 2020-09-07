package TP.Models.Genetics.Selections;

import TP.Interfaces.ISelection;
import lombok.Getter;
import lombok.Setter;


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
