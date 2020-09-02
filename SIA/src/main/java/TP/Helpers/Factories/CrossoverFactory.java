package TP.Helpers.Factories;

import TP.Configuration.MutationConfiguration;
import TP.Constants.Constants;
import TP.Interfaces.ICrossover;
import TP.Interfaces.IMutation;
import TP.Models.Genetics.Crossovers.AnularCrossOver;
import TP.Models.Genetics.Crossovers.OnePointCrossOver;
import TP.Models.Genetics.Crossovers.TwoPointsCrossOver;
import TP.Models.Genetics.Crossovers.UniformCrossOver;
import TP.Models.Genetics.Mutations.CompleteMultiGenMutation;
import TP.Models.Genetics.Mutations.GenMutation;
import TP.Models.Genetics.Mutations.LimitedMultiGenMutation;
import TP.Models.Genetics.Mutations.UniformMultiGenMutation;

public class CrossoverFactory {

    public static ICrossover giveCrossover(String crossover) {
        switch (crossover) {
            case Constants.Crossover.AnularCrossOver:
                return new AnularCrossOver();
            case Constants.Crossover.OnePointCrossOver:
                return new OnePointCrossOver();
            case Constants.Crossover.TwoPointsCrossOver:
                return new TwoPointsCrossOver();
            case Constants.Crossover.UniformCrossOver:
                return new UniformCrossOver();
            default:
                throw new IllegalArgumentException("Invalid crossover");
        }
    }
}
