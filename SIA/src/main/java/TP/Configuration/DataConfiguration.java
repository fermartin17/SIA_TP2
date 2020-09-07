package TP.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataConfiguration {
    private boolean setData;
    private String helmetPath;
    private String frontPath;
    private String weaponsPath;
    private String glovesPath;
    private String bootsPath;


    public DataConfiguration(){
        this.helmetPath = new String();
        this.bootsPath = new String();
        this.weaponsPath = new String();
        this.frontPath = new String();
        this.glovesPath = new String();
    }
}
