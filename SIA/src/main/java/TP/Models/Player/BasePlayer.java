package TP.Models.Player;

import TP.Constants.Constants;
import TP.Models.Equipment;
import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public abstract class BasePlayer {

    private String name;
    private List<Equipment> equipment;
    private Chromosome chromosome;
    private double atm;
    private double dem;
    private int height;
    private double attack;
    private double defense;
    private double performance;
    private double strength;
    private double agility;
    private double pericia;
    private double health;
    private double resistance;

    //constructor default
    public BasePlayer(){
        this.name = "";
        this.equipment = new ArrayList<>(Chromosome.S -1);
        this.chromosome = new Chromosome();
        this.atm = dem = height = 0;
        this.attack = defense = performance = 0;
        this.strength = this.agility = this.pericia = 0;
        this.health = this.resistance = 0;
    }

    public BasePlayer(String name){
        this.name = name;
        this.equipment = new ArrayList<>(Chromosome.S -1);
        this.chromosome = new Chromosome();
        this.atm = dem = height = 0;
        this.attack = defense = performance = 0;
        this.strength = this.agility = this.pericia = 0;
        this.health = this.resistance = 0;
    }

    public BasePlayer(Chromosome c){
        this.height = chromosome.getChromosome()[0];
        this.equipment.add(new Equipment(Constants.Equipment.weapons,chromosome.getChromosome()[1]));
        this.equipment.add(new Equipment(Constants.Equipment.boots,chromosome.getChromosome()[2]));
        this.equipment.add(new Equipment(Constants.Equipment.helmet,chromosome.getChromosome()[3]));
        this.equipment.add(new Equipment(Constants.Equipment.gloves,chromosome.getChromosome()[4]));
        this.equipment.add(new Equipment(Constants.Equipment.front,chromosome.getChromosome()[5]));
        this.chromosome = c;
    }

    public abstract Double calculatePerformance();

    public void calculateAttack() {
        this.attack = (this.agility + this.pericia) * this.strength * this.atm;
    }

    public void calculateDefense() {
        this.defense = (this.resistance + this.pericia) * this.health * this.dem;
    }

    public void calculateStrength() {
        double equipmentStrength = equipment.stream().mapToDouble(Equipment::getStrength).sum();
        this.strength = 100 * Math.tanh(0.01 * equipmentStrength);
    }

    public void calculateAgility() {
        double equipmentAgility = equipment.stream().mapToDouble(Equipment::getAgility).sum();
        this.agility = Math.tanh(0.01 * equipmentAgility);
    }

    public void calculatePericia() {
        double equipmentPericia = equipment.stream().mapToDouble(Equipment::getPericia).sum();
        this.pericia = 0.6 * Math.tanh(0.01 * equipmentPericia);
    }

    public void calculateHealth() {
        double equipmentHealth = equipment.stream().mapToDouble(Equipment::getHealth).sum();
        this.health = 100 * Math.tanh(0.01 * equipmentHealth);
    }

    public void calculateResistance() {
        double equipmentResistance = equipment.stream().mapToDouble(Equipment::getResistence).sum();
        this.resistance = Math.tanh(0.01 * equipmentResistance);
    }

    public void calculateATM() {
        double heightInMeters =  (double) this.height / 100;
        double aux = 3 * heightInMeters - 5;
        this.atm = 0.7  - Math.pow(aux, 4) + Math.pow(aux, 2) + heightInMeters / 4;
    }

    public void calculateDEM() {
        double heightInMeters =  (double) this.height / 100;
        double aux = 2.5 * heightInMeters - 4.16;
        this.dem = 1.9  + Math.pow(aux, 4) + Math.pow(aux, 2) - 3 * heightInMeters / 10;
    }

    public BasePlayer comparePerformance(BasePlayer player){
        return this.getPerformance() >= player.getPerformance() ? this : player;
    }

    public void CalculateAll(){
        calculateATM();
        calculateDEM();
        calculateAgility();
        calculateHealth();
        calculatePericia();
        calculateResistance();
        calculateStrength();
        calculateAttack();
        calculateDefense();
        this.chromosome.getChromosome()[0] = this.height;
        this.chromosome.getChromosome()[1] = Objects.requireNonNull(this.equipment.stream().filter(x -> x.getName().equals(Constants.Equipment.weapons)).findFirst().orElse(null)).getId();
        this.chromosome.getChromosome()[2] = Objects.requireNonNull(this.equipment.stream().filter(x -> x.getName().equals(Constants.Equipment.boots)).findFirst().orElse(null)).getId();
        this.chromosome.getChromosome()[3] = Objects.requireNonNull(this.equipment.stream().filter(x -> x.getName().equals(Constants.Equipment.helmet)).findFirst().orElse(null)).getId();
        this.chromosome.getChromosome()[4] = Objects.requireNonNull(this.equipment.stream().filter(x -> x.getName().equals(Constants.Equipment.gloves)).findFirst().orElse(null)).getId();
        this.chromosome.getChromosome()[5] = Objects.requireNonNull(this.equipment.stream().filter(x -> x.getName().equals(Constants.Equipment.front)).findFirst().orElse(null)).getId();
    }
}
