package TP.Configuration;

import lombok.Getter;
import lombok.Setter;

import javax.naming.SizeLimitExceededException;

@Setter
@Getter
public class SelectionMethod {
    private double percentage;
    private String name;
    private double arg1;
    private double arg2;

    public SelectionMethod(){
        this.name = new String();
    }
}
