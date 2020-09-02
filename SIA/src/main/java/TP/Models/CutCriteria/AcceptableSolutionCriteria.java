package TP.Models.CutCriteria;

import TP.Interfaces.ICutCriteria;
import TP.Models.Generation;

public class AcceptableSolutionCriteria implements ICutCriteria {

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
