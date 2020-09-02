package TP.Models.CutCriteria;

import TP.Interfaces.ICutCriteria;
import TP.Models.Generation;

public class TimeCriteria implements ICutCriteria {

    private double startTime;
    private final double maxTime;

    public TimeCriteria(double maxTime){
        this.startTime = -1;
        this.maxTime = maxTime;
    }

    @Override
    public boolean cutProgram(Generation g) {
        if(this.startTime == -1){
            this.startTime = System.currentTimeMillis();
            return false;
        }
        return (System.currentTimeMillis() - startTime) > maxTime;
    }
}
