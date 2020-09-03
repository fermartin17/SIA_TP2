package TP.Models.Player;

import TP.Constants.Constants;
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
        return 0.3 * getAttack() + 0.8 * getDefense();
    }
}
