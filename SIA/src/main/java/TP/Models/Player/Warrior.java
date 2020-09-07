package TP.Models.Player;

import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Warrior extends BasePlayer {

    public Warrior(String name) { super(name);}
    public Warrior(){super();}

    public Warrior(Chromosome c) { super(c); }

    @Override
    public Double calculatePerformance() {
        this.setPerformance(0.6 * getAttack() + 0.6 * getDefense());
        return this.getPerformance();
    }

    @Override
    public void calculateAll() {
        super.calculateAll();
        calculatePerformance();
    }
}
