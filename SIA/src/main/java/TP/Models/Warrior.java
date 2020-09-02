package TP.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Warrior extends BasePlayer {

    public Warrior(double height){
        setHeight(height);
    }
    @Override
    public Double calculatePerformance() {
        return 0.6 * getAttack() + 0.6 * getDefense();
    }
}
