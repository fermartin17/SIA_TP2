package TP.Models.Genetics.Mutations;

import TP.Interfaces.IMutation;
import lombok.Getter;

@Getter
public abstract class Mutation implements IMutation {

    private final double mutationProbability;

    public Mutation(double mutationProbability){
        this.mutationProbability = mutationProbability;
    }
}
