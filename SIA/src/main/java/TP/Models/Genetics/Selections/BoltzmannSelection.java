package TP.Models.Genetics.Selections;

import TP.Models.Player.BasePlayer;

import java.util.List;

public class BoltzmannSelection extends Selection{

    public BoltzmannSelection(int K, double percentage) {
        super(K, percentage);
    }

    @Override
    public List<BasePlayer> makeSelection(List<BasePlayer> population) {
        return null;
    }
}
