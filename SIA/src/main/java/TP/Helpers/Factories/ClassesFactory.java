package TP.Helpers.Factories;

import TP.Constants.Constants;
import TP.Models.Player.*;

public class ClassesFactory {

    public static BasePlayer givePlayer(String player) {
        switch (player.toLowerCase()) {
            case Constants.Type.Warrior:
                return new Warrior();
            case Constants.Type.Archer:
                return new Archer();
            case Constants.Type.Defender:
                return new Defender();
            case Constants.Type.Infiltrate:
                return new Infiltrate();
            default:
                throw new IllegalArgumentException("Invalid player");
        }
    }
}
