package TP.Helpers.Factories;

import TP.Constants.Constants;
import TP.Interfaces.ICrossover;
import TP.Interfaces.IFillMethod;
import TP.Interfaces.IMutation;
import TP.Interfaces.IService;
import TP.Models.CutCriteria.*;
import TP.Models.FillAll;
import TP.Models.FillParent;
import TP.Models.Genetics.Mutations.Mutation;

public class FillMethodFactory {

    public static IFillMethod giveMethod(String name, ICrossover crossover, Mutation mutation, IService service){
        switch (name.toLowerCase()) {
            case Constants.FillMethod.FillAll:
                return new FillAll(crossover, mutation, service);
            case Constants.FillMethod.FillParent:
                return new FillParent(crossover, mutation, service);
            default:
                throw new IllegalArgumentException("Invalid fill method");
        }
    }
}
