package TP.Models.CutCriteria;

import TP.Interfaces.ICutCriteria;
import TP.Models.Generation;

public class ContentCriteria implements ICutCriteria {

    private final int maxHits;
    private final double precision;
    private int currentHits;

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
