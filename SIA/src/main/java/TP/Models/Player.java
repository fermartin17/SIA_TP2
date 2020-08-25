package TP.Models;

import TP.Interfaces.IPlayer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Player implements IPlayer {
    private Equipment equipment;
    private double height;
    private double attack;
    private double defense;
    private double performance;

    @Override
    public Double calculateAttack() {
        return null;
    }

    @Override
    public Double calculateDefense() {
        return null;
    }

    @Override
    public Double calculatePerformance() {
        return null;
    }
}
