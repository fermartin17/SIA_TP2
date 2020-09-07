package TP.Models.CutCriteria;

import TP.Models.Generation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberOfGenerationsCriteria extends BaseCutCriteria {

    private int maxCount;

    public NumberOfGenerationsCriteria(){}

    public NumberOfGenerationsCriteria(int maxCount){
        this.maxCount = maxCount;
    }

    @Override
    public boolean cutProgram(Generation g) {
        return g.getGenerationNumber() == maxCount;
    }
}
