package TP.Models.Genetics.Selections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UniversalSelection extends RouletteSelection {

    public  UniversalSelection (int K, double percentage){
        super(K, percentage);
    }

    @Override
    public List<Double> generateRandoms() {
        List<Double> ret = new ArrayList<>(this.getK());
        for(int i = 0; i < this.getK(); i++){
            double aux = ThreadLocalRandom.current().nextDouble();
            ret.add( (aux + (double) i) / (double) this.getK());
        }
        return ret;
    }
}
