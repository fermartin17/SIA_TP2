package TP.Models.Player;

import TP.Constants.Constants;
import TP.Models.Equipment;
import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Warrior extends BasePlayer {

    public Warrior() { super();}

    public Warrior(Chromosome c) { super(c); }

    @Override
    public Double calculatePerformance() {
        return 0.6 * getAttack() + 0.6 * getDefense();
    }
}
