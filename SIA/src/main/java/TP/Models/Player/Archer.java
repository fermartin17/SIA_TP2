package TP.Models.Player;

import TP.Constants.Constants;
import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Archer extends BasePlayer {

    public Archer(){super();}
    public Archer(String name){super(name);}

    public Archer(Chromosome chromosome){
        super(chromosome);
    }

    @Override
    public Double calculatePerformance() {
        this.setPerformance(0.9 * getAttack() + 0.1 * getDefense());
        return this.getPerformance();
    }

    @Override
    public void CalculateAll() {
        super.CalculateAll();
        calculatePerformance();
    }
}
