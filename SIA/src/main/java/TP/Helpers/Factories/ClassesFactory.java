package TP.Helpers.Factories;

import TP.Constants.Constants;
import TP.Interfaces.ICrossover;
import TP.Models.*;
import TP.Models.Genetics.Crossovers.AnularCrossOver;
import TP.Models.Genetics.Crossovers.OnePointCrossOver;
import TP.Models.Genetics.Crossovers.TwoPointsCrossOver;
import TP.Models.Genetics.Crossovers.UniformCrossOver;

public class ClassesFactory {

    public static BasePlayer givePlayer(String player, double height) {
        switch (player.toLowerCase()) {
            case Constants.Type.Warrior:
                return new Warrior(height);
            case Constants.Type.Archer:
                return new Archer(height);
            case Constants.Type.Defender:
                return new Defender(height);
            case Constants.Type.Infiltrate:
                return new Infiltrate(height);
            default:
                throw new IllegalArgumentException("Invalid player");
        }
    }
}
