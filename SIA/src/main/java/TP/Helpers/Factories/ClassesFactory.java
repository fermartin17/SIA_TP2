package TP.Helpers.Factories;

import TP.Constants.Constants;
import TP.Models.Player.*;

public class ClassesFactory {

    public static BasePlayer givePlayer(String player) {
        switch (player.toLowerCase()) {
            case Constants.Type.Warrior:
                return new Warrior(player);
            case Constants.Type.Archer:
                return new Archer(player);
            case Constants.Type.Defender:
                return new Defender(player);
            case Constants.Type.Infiltrate:
                return new Infiltrate(player);
            default:
                throw new IllegalArgumentException("Invalid player");
        }
    }
}
