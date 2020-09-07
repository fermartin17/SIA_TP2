package TP.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CutCriteriaMethod {
    private String name;
    private double arg1;
    private double arg2;

    public CutCriteriaMethod(){
        this.name = "";
        this.arg1 = arg2 = 0;
    }

}
