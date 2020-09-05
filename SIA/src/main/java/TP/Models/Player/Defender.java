package TP.Models.Player;

import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Defender extends BasePlayer {

    public Defender(String name){super(name);}
    public Defender(){super();}

    public Defender(Chromosome chromosome){
        super(chromosome);
    }

    @Override
    public Double calculatePerformance() {
        this.setPerformance(0.3 * getAttack() + 0.8 * getDefense());
        return this.getPerformance();
    }

    @Override
    public void calculateAll() {
        super.calculateAll();
        calculatePerformance();
    }
}
