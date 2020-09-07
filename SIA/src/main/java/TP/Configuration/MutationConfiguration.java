package TP.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MutationConfiguration {
    private String name;
    private double probability;

    public MutationConfiguration(){
        this.name = new String();
    };
}
