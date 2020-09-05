package TP.Models.Selection;

import TP.Models.Genetics.Selections.EliteSelection;
import TP.Models.Genetics.Selections.RouletteSelection;
import TP.Models.Player.BasePlayer;
import TP.Models.Player.Warrior;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouletteSelectionTest {

    RouletteSelection rouletteSelection;
    List<BasePlayer> population;
    List<Double> relatives;
    List<Double> accumulated;

    @Before
    public void setup(){
        rouletteSelection = new RouletteSelection(3, 1);
        population = new ArrayList<>();
        BasePlayer aux;
        aux = new Warrior();
        aux.setPerformance(10.2233);
        population.add(aux);
        aux = new Warrior();
        aux.setPerformance(15.42321);
        population.add(aux);
        aux = new Warrior();
        aux.setPerformance(9.56564);
        population.add(aux);
        aux = new Warrior();
        aux.setPerformance(18.21334);
        population.add(aux);
        aux = new Warrior();
        aux.setPerformance(13.12355);
        population.add(aux);
        relatives = new ArrayList<>(5);
        relatives.add(0.153620548);
        relatives.add(0.231757062);
        relatives.add(0.143738211);
        relatives.add(0.273682986);
        relatives.add(0.197201191);
        accumulated = new ArrayList<>(5);
        accumulated.add(0.153620548);
        accumulated.add(0.38537761);
        accumulated.add(0.529115821);
        accumulated.add(0.802798807);
        accumulated.add(0.999999998);
    }

    @Test
    public void relativePerformanceTest(){
        double total = 66.54904;
        List<Double> expected = new ArrayList<>(5);
        expected.add(0.153620548);
        expected.add(0.231757062);
        expected.add(0.143738211);
        expected.add(0.273682986);
        expected.add(0.197201191);
        List<Double> result = rouletteSelection.calculateRelativePerformance(population, total);
        Assert.assertEquals(5, result.size());
        for(int i = 0; i < expected.size(); i++){
            Assert.assertEquals(expected.get(i), result.get(i), 0.00000001);
        }
    }

    @Test
    public void accumulatedPerformanceTest(){
        List<Double> expected = accumulated;
        List<Double> result = rouletteSelection.calculateAccumulatedList(relatives);
        Assert.assertEquals(5, result.size());
        for(int i = 0; i < expected.size(); i++){
            Assert.assertEquals(expected.get(i), result.get(i), 0.0000001);
        }
    }

    @Test
    public void selectionTest(){
        List<Double> randoms = new ArrayList<>();
        randoms.add(0.11456787);
        randoms.add(0.40078123);
        randoms.add(0.99456785);
        List<BasePlayer> expected = new ArrayList<>(3);
        expected.add(population.get(0));
        expected.add(population.get(2));
        expected.add(population.get(4));
        List<BasePlayer> result = rouletteSelection.selectPopulation(accumulated, randoms, population);
        Assert.assertEquals(3, result.size());
        Assert.assertArrayEquals(expected.toArray(), result.toArray());
    }

}
