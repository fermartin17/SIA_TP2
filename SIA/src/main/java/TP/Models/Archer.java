package TP.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Archer extends BasePlayer {

    public Archer(double height){
        setHeight(height);
    }
    @Override
    public Double calculatePerformance() {
        return 0.9 * getAttack() + 0.1 * getDefense();
    }
}
