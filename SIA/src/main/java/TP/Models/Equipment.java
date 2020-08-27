package TP.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Equipment {
    private int id;
    private String name;
    private double health;
    private double strength;
    private double agility;
    private double pericia;
    private double resistence;

    public Equipment(int id, String name, double health, double strength, double agility, double pericia, double resistence){
        this.id = id;
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.pericia = pericia;
        this.resistence = resistence;
    }
}
