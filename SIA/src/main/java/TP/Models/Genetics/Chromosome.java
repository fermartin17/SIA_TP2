package TP.Models.Genetics;

import TP.Interfaces.IMutation;
import lombok.Getter;

import java.util.Arrays;

@Getter

public class Chromosome {

    public static final int S = 6;
    private int chromosome[];

    public Chromosome(){
        this.chromosome = new int[S];
    }

    public Chromosome(Chromosome c){
        this.chromosome = new int[S];
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(c.chromosome, 0, this.chromosome, 0, S);
    }

    public Chromosome(int height, int weaponId, int bootsId,
                      int helmetId, int glovesId, int armourId){
        this.chromosome = new int[S];
        this.chromosome[0] = height;
        this.chromosome[1] = weaponId;
        this.chromosome[2] = bootsId;
        this.chromosome[3] = helmetId;
        this.chromosome[4] = glovesId;
        this.chromosome[5] = armourId;
    }

    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        if(o == this) return true;
        Chromosome c = (Chromosome) o;
        return Arrays.equals(chromosome, c.chromosome);
    }

//    public String toString(){
//        StringBuilder ret = new StringBuilder();
//        Arrays.stream(chromosome).forEach(i -> ret.append(i.toString()).append(" "));
//        return ret.append("\n").toString();
//    }

}
