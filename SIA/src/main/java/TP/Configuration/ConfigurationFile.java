package TP.Configuration;

import TP.Models.CutCriteria.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigurationFile {

    private SelectionMethod fatherMethod_1;
    private SelectionMethod fatherMethod_2;
    private SelectionMethod individualMethod_1;
    private SelectionMethod individualMethod_2;
    private String crossoverMethod;
    private MutationConfiguration mutation;
    private CutCriteriaMethod criteria;
    private int population;
    private int generationNumber;
    private String individualClass;

    public ConfigurationFile(){
        this.fatherMethod_1 = new SelectionMethod();
        this.fatherMethod_2 = new SelectionMethod();
        this.individualMethod_1 = new SelectionMethod();
        this.individualMethod_2 = new SelectionMethod();
        this.mutation = new MutationConfiguration();
        this.criteria = new CutCriteriaMethod();
        this.individualClass = "";
        this.crossoverMethod = "";
    }
}
