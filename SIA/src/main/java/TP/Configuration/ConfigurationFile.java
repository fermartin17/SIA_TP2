package TP.Configuration;

import TP.Models.BasePlayer;
import TP.Models.CutCriteria.*;
import TP.Models.Genetics.Mutations.Mutation;
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

    private AcceptableSolutionCriteria acceptableSolutionCriteria;
    private ContentCriteria contentCriteria;
    private NumberOfGenerationsCriteria numberOfGenerationsCriteria;
    private StructureCriteria structureCriteria;
    private TimeCriteria timeCriteria;

    private int height;
    private String individualClass;

    public ConfigurationFile(){
        this.fatherMethod_1 = new SelectionMethod();
        this.fatherMethod_2 = new SelectionMethod();
        this.individualMethod_1 = new SelectionMethod();
        this.individualMethod_2 = new SelectionMethod();
        this.mutation = new MutationConfiguration();
        this.acceptableSolutionCriteria = new AcceptableSolutionCriteria();
        this.contentCriteria = new ContentCriteria();
        this.numberOfGenerationsCriteria = new NumberOfGenerationsCriteria();
        this.structureCriteria = new StructureCriteria();
        this.timeCriteria = new TimeCriteria();
        this.individualClass = new String();
        this.crossoverMethod = new String();
    }
}
