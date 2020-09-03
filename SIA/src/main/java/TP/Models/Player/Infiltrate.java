package TP.Models.Player;

import TP.Constants.Constants;
import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Infiltrate extends BasePlayer {

    public Infiltrate(){super();}

    public Infiltrate(Chromosome chromosome){
        super(chromosome);
    }

    @Override
    public Double calculatePerformance() {
        return 0.8 * getAttack() + 0.3 * getDefense();
    }
}
