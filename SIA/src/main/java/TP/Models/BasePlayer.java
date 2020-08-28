package TP.Models;

import TP.Interfaces.IMutation;
import TP.Models.Equipment;
import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Setter
@Getter
public abstract class BasePlayer {

    private String name;
    private List<Equipment> equipment;
    private Chromosome chromosome;
    private double atm;
    private double dem;
    private double height;
    private double attack;
    private double defense;
    private double performance;
    private double strength;
    private double agility;
    private double pericia;
    private double health;
    private double resistance;
    private double mutationProbability = 0.5;
    private IMutation mutation;

    public abstract Double calculatePerformance();

    public void calculateAttack() {
        this.attack = (this.agility + this.pericia) * this.strength * this.atm;
    }

    public void calculateDefense() {
        this.defense = (this.resistance + this.pericia) * this.health * this.dem;
    }

    public void calculateStrength() {
        double equipmentStrength = 0;
        for (Equipment eq : equipment)
            equipmentStrength += eq.getStrength();

        this.strength = 100 * Math.tanh(0.01 * equipmentStrength);
    }

    public void calculateAgility() {
        double equipmentAgility = 0;
        for (Equipment eq : equipment)
            equipmentAgility += eq.getAgility();

        this.agility = Math.tanh(0.01 * equipmentAgility);
    }

    public void calculatePericia() {
        double equipmentPericia = 0;
        for (Equipment eq : equipment)
            equipmentPericia += eq.getPericia();

        this.pericia = 0.6 * Math.tanh(0.01 * equipmentPericia);
    }

    public void calculateHealth() {
        double equipmentHealth = 0;
        for (Equipment eq : equipment)
            equipmentHealth += eq.getHealth();

        this.health = 100 * Math.tanh(0.01 * equipmentHealth);
    }

    public void calculateResistance() {
        double equipmentResistance = 0;
        for (Equipment eq : equipment)
            equipmentResistance += eq.getResistence();

        this.resistance = Math.tanh(0.01 * equipmentResistance);
    }

    public void calculateATM() {
        this.atm = 0.7 - Math.pow((3 * this.height - 5), 4) + Math.pow((3 * this.height - 5), 2) + this.height / 4;
    }

    public void calculateDEM() {
        this.dem = 1.9 + Math.pow((2.5 * this.height - 4.16), 4) + Math.pow((2.5 * this.height - 4.16), 2) + (3 * this.height) / 10;
    }

    public void mutate(){
        double rand = ThreadLocalRandom.current().nextDouble(0, 1);
        if(rand > mutationProbability){
            this.chromosome = mutation.mutate(this.chromosome);
        }
        //change stats based on mutatation
    }
}
