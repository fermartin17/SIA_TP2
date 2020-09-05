package TP.Models.Selection;

import TP.Models.Genetics.Selections.EliteSelection;
import TP.Models.Player.BasePlayer;
import TP.Models.Player.Warrior;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EliteSelectionTest {

    EliteSelection eliteSelection;
    List<BasePlayer> population;

    @Before
    public void setup(){
        eliteSelection = new EliteSelection(3, 1);
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
    }

    @Test
    public void best3Test(){
        List<BasePlayer> expected = new ArrayList<>(3);
        expected.add(population.get(3));
        expected.add(population.get(1));
        expected.add(population.get(4));
        List<BasePlayer> result = eliteSelection.makeSelection(population);
        Assert.assertEquals(3, result.size());
        Assert.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void best6Test(){
        eliteSelection.setK(6);
        List<BasePlayer> expected = new ArrayList<>(6);
        expected.add(population.get(3));
        expected.add(population.get(1));
        expected.add(population.get(4));
        expected.add(population.get(0));
        expected.add(population.get(2));
        expected.add(population.get(3));
        List<BasePlayer> result = eliteSelection.makeSelection(population);
        Assert.assertEquals(6, result.size());
        Assert.assertArrayEquals(expected.toArray(), result.toArray());
    }
}
