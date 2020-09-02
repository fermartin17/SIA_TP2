package TP.Models.Genetics.Selections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UniversalSelection extends RouletteSelection {

    public  UniversalSelection (double probability){
        super(probability);
    }
    @Override
    public List<Double> generateRandoms() {
        List<Double> ret = new ArrayList<>(K);
        for(int i = 0; i < K; i++){
            double aux = ThreadLocalRandom.current().nextDouble();
            ret.add( (aux + (double) i) / (double) K);
        }
        return ret;
    }
}
