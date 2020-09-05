package TP.Models.Player;

import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Infiltrate extends BasePlayer {

    public Infiltrate(String name){super(name);}
    public Infiltrate(){super();}

    public Infiltrate(Chromosome chromosome){
        super(chromosome);
    }

    @Override
    public Double calculatePerformance() {
        this.setPerformance(0.8 * getAttack() + 0.3 * getDefense());
        return this.getPerformance();
    }

    @Override
    public void calculateAll() {
        super.calculateAll();
        calculatePerformance();
    }
}
