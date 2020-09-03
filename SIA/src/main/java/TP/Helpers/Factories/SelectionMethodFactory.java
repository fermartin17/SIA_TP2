package TP.Helpers.Factories;

import TP.Configuration.SelectionMethod;
import TP.Constants.Constants;
import TP.Models.Selection;
import TP.Models.Genetics.Selections.*;

public class SelectionMethodFactory {

    public static Selection giveSelection(SelectionMethod method) {
        switch (method.getName().toLowerCase()) {
            case Constants.Selection.DeterministicTournament:
                return new DeterministicTournament(method.getPercentage());
            case Constants.Selection.EliteSelection:
                return new EliteSelection(method.getPercentage());
            case Constants.Selection.ProbabilisticTournament:
                return new ProbabilisticTournament(method.getPercentage());
            case Constants.Selection.RouletteSelection:
                return new RouletteSelection(method.getPercentage());
            case Constants.Selection.UniversalSelection:
                return new UniversalSelection(method.getPercentage());
            default:
                throw new IllegalArgumentException("Invalid crossover");
        }
    }
}
