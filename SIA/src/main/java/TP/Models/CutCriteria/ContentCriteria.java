package TP.Models.CutCriteria;

import TP.Interfaces.ICutCriteria;
import TP.Models.BaseCutCriteria;
import TP.Models.Generation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentCriteria extends BaseCutCriteria implements ICutCriteria {

    private int maxHits;
    private double precision;
    private int currentHits;

    public ContentCriteria(){}

    public ContentCriteria(int maxHits, double precision){
        this.maxHits = maxHits;
        this.precision = precision;
        this.currentHits = 0;
    }

    @Override
    public boolean cutProgram(Generation g) {
        if(g.getBestFitness() - g.getCurrentFitness() < precision){
            this.currentHits++;
        }else{
            currentHits = 0;
        }
        return currentHits >= maxHits;
    }
}
