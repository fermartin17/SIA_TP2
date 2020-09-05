package TP.Helpers.Factories;

import TP.Configuration.SelectionMethod;
import TP.Constants.Constants;
import TP.Models.Genetics.Selections.Selection;
import TP.Models.Genetics.Selections.*;

public class SelectionMethodFactory {

    public static Selection giveSelection(SelectionMethod method, int populationQuantity, double arg1, double arg2) {
        switch (method.getName().toLowerCase()) {
            case Constants.Selection.DeterministicTournament:
                return new DeterministicTournament(populationQuantity, method.getPercentage(), (int) arg1);
            case Constants.Selection.EliteSelection:
                return new EliteSelection(populationQuantity, method.getPercentage());
            case Constants.Selection.ProbabilisticTournament:
                return new ProbabilisticTournament(populationQuantity, method.getPercentage(), arg1);
            case Constants.Selection.RouletteSelection:
                return new RouletteSelection(populationQuantity, method.getPercentage());
            case Constants.Selection.UniversalSelection:
                return new UniversalSelection(populationQuantity, method.getPercentage());
            case Constants.Selection.BoltzmanSelection:
                return new BoltzmanSelection(populationQuantity, method.getPercentage(), arg1, arg2);
            case Constants.Selection.RankingSelection:
                return new RankingSelection(populationQuantity, method.getPercentage());
            default:
                throw new IllegalArgumentException("Invalid crossover");
        }
    }
}
