package TP.Helpers.Factories;

import TP.Configuration.MutationConfiguration;
import TP.Constants.Constants;
import TP.Interfaces.IMutation;
import TP.Models.Genetics.Mutations.*;

public class MutationFactory {

    public static Mutation giveMutation(MutationConfiguration mutation){
        switch (mutation.getName().toLowerCase()){
            case Constants.Mutation.CompleteMultiGenMutation:
                return new CompleteMultiGenMutation(mutation.getProbability());
            case Constants.Mutation.GenMutation:
                return new GenMutation(mutation.getProbability());
            case Constants.Mutation.LimitedMultiGenMutation:
                return new LimitedMultiGenMutation(mutation.getProbability());
            case Constants.Mutation.UniformMultiGenMutation:
                return new UniformMultiGenMutation(mutation.getProbability());
            default:
                throw new IllegalArgumentException("Invalid mutation");
        }
    }
}
