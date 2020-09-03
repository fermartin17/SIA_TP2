package TP.Models.CutCriteria;

import TP.Models.Generation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeCriteria extends BaseCutCriteria {

    private double startTime;
    private double maxTime;

    public TimeCriteria(){}

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
