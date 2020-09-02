package TP.Models.CutCriteria;

import TP.Interfaces.ICutCriteria;
import TP.Models.BaseCutCriteria;
import TP.Models.Generation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptableSolutionCriteria extends BaseCutCriteria implements ICutCriteria {

    public double acceptableFitness;

    public AcceptableSolutionCriteria(){}

    public AcceptableSolutionCriteria(double acceptableFitness){
        this.acceptableFitness = acceptableFitness;
    }

    @Override
    public boolean cutProgram(Generation g) {
        return g.getBestFitness() >= acceptableFitness;
    }
}
