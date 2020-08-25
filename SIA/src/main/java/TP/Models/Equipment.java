package TP.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Equipment {
    private double health;
    private double atr2;
    private double atr3;
    private double atr4;
    private double atr5;

    public Equipment(double health, double atr2, double atr3, double atr4, double atr5){
        this.health = health;
        this.atr2 = atr2;
        this.atr3 = atr3;
        this.atr4 *= atr4;
        this.atr5 = atr5;
    }
}
