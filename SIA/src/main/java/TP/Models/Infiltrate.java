package TP.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Infiltrate extends BasePlayer {

    public Infiltrate(){
        super();
    }
    @Override
    public Double calculatePerformance() {
        return 0.8 * getAttack() + 0.3 * getDefense();
    }
}
