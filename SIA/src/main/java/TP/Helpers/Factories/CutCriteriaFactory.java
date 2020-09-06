package TP.Helpers.Factories;

import TP.Configuration.CutCriteriaMethod;
import TP.Constants.Constants;
import TP.Models.CutCriteria.*;

public class CutCriteriaFactory {

    public static BaseCutCriteria giveCriteria(CutCriteriaMethod method) {
        switch (method.getName().toLowerCase()) {
            case Constants.Criteria.AcceptableSolutionCriteria:
                return new AcceptableSolutionCriteria(method.getArg1());
            case Constants.Criteria.ContentCriteria:
                return new ContentCriteria((int) method.getArg1(), method.getArg2());
            case Constants.Criteria.NumberOfGenerationsCriteria:
                return new NumberOfGenerationsCriteria((int) method.getArg1());
            case Constants.Criteria.StructureCriteria:
                return new StructureCriteria(method.getArg1(), (int) method.getArg2());
            case Constants.Criteria.TimeCriteria:
                return new TimeCriteria(method.getArg1());
            default:
                throw new IllegalArgumentException("Invalid criteria");
        }
    }
}
