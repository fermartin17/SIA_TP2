package TP.Models;

import TP.Constants.Constants;
import TP.Models.Genetics.Chromosome;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Defender extends BasePlayer {

    public Defender(){}

    public Defender(Chromosome chromosome){
        this.setHeight(chromosome.getChromosome()[0]);
        this.getEquipment().add(new Equipment(Constants.Equipment.weapons,chromosome.getChromosome()[1]));
        this.getEquipment().add(new Equipment(Constants.Equipment.boots,chromosome.getChromosome()[2]));
        this.getEquipment().add(new Equipment(Constants.Equipment.helmet,chromosome.getChromosome()[3]));
        this.getEquipment().add(new Equipment(Constants.Equipment.gloves,chromosome.getChromosome()[4]));
        this.getEquipment().add(new Equipment(Constants.Equipment.front,chromosome.getChromosome()[5]));
    }

    @Override
    public Double calculatePerformance() {
        return 0.3 * getAttack() + 0.8 * getDefense();
    }
}
