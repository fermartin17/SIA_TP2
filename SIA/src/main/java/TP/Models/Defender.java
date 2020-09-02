package TP.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Defender extends BasePlayer {

    public Defender(double height){
        setHeight(height);
    }
    @Override
    public Double calculatePerformance() {
        return 0.3 * getAttack() + 0.8 * getDefense();
    }
}
