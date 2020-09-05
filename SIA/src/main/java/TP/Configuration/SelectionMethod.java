package TP.Configuration;

import lombok.Getter;
import lombok.Setter;

import javax.naming.SizeLimitExceededException;

@Setter
@Getter
public class SelectionMethod {
    private double percentage;
    private String name;
    private double boltzmanT0;
    private double boltzmanTC;

    public SelectionMethod(){
        this.name = new String();
    }
}
