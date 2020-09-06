package TP.Models.CutCriteria;

import TP.Models.Generation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptableSolutionCriteria extends BaseCutCriteria{

    public double acceptableFitness;

    public AcceptableSolutionCriteria(){}

    public AcceptableSolutionCriteria(double acceptableFitness){
        this.acceptableFitness = acceptableFitness;
    }

    @Override
    public boolean cutProgram(Generation g) {
        return g.getBestFitness() != null &&
               g.getBestFitness().getPerformance() >= acceptableFitness;
    }
}
