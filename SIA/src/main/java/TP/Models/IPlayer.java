package TP.Models;

import TP.Models.Equipment;

import java.util.List;

public abstract class IPlayer {

    private String name;
    private List<Equipment> equipment;
    private double height;
    private double attack;
    private double defense;
    private double performance;
    private double strength;
    private double agility;
    private double pericia;
    private double health;
    private double resistence;

    public abstract Double calculateAttack();
    public abstract Double calculateDefense();
    public abstract Double calculatePerformance();
    public abstract Double calculateStrength();
    public abstract Double calculateAgility();
    public abstract Double calculatePericia();
    public abstract Double calculateHealth();
    public abstract Double calculateResistence();
}
