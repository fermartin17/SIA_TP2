package TP.Models;

import TP.Interfaces.IPlayer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Player implements IPlayer {
    private String name;
    private Equipment equipment;
    private double height;
    private double attack;
    private double defense;
    private double performance;
    private double strength;
    private double agility;
    private double pericia;
    private double health;
    private double resistence;

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

    @Override
    public Double calculateStrength() {
        return null;
    }

    @Override
    public Double calculateAgility() {
        return null;
    }

    @Override
    public Double calculatePericia() {
        return null;
    }

    @Override
    public Double calculateHealth() {
        return null;
    }

    @Override
    public Double calculateResistence() {
        return null;
    }
}
