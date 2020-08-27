package TP.Models.Genetics;

import TP.Interfaces.IMutation;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class Chromosome implements IMutation {

    private Integer[] chromosome;
    public static final int S = 6;

    public Chromosome(){
        this.chromosome = new Integer[S];
    }

    public Chromosome(Chromosome c){
        this.chromosome = new Integer[S];
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(c.chromosome, 0, this.chromosome, 0, S);
    }

    public Chromosome(int height, int weaponId, int bootsId,
                      int helmetId, int glovesId, int armourId){
        this.chromosome = new Integer[S];
        this.chromosome[0] = height;
        this.chromosome[1] = weaponId;
        this.chromosome[2] = bootsId;
        this.chromosome[3] = helmetId;
        this.chromosome[4] = glovesId;
        this.chromosome[5] = armourId;
    }


}
