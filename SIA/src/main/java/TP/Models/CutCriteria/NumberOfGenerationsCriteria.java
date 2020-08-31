package TP.Models.CutCriteria;

import TP.Interfaces.ICutCriteria;
import TP.Models.Generation;

public class NumberOfGenerationsCriteria implements ICutCriteria {

    private final int maxCount;

    public NumberOfGenerationsCriteria(int maxCount){
        this.maxCount = maxCount;
    }

    @Override
    public boolean cutProgram(Generation g) {
        return g.getGenerationNumber() == maxCount;
    }
}
