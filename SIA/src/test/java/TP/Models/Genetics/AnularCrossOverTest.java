package TP.Models.Genetics;

import TP.Interfaces.ICrossover;
import TP.Models.Genetics.Crossovers.AnularCrossOver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnularCrossOverTest {

    private Chromosome p1, p2;

    private ICrossover anularCrossOver;

    @Before
    public void setup(){
        p1 = new Chromosome(123, 13, 55, 44, 33,45);
        p2 = new Chromosome(154, 33, 65, 44, 21,76);
        anularCrossOver = new AnularCrossOver();
    }

    @Test
    public void nullTest(){
        Assert.assertNull(anularCrossOver.cross(p1, null));
        Assert.assertNull(anularCrossOver.cross(null, p2));
        Assert.assertNull(anularCrossOver.cross(null, null));
    }

    @Test
    public void unalteredTest(){
        Chromosome[] expected = new Chromosome[2];
        expected[0] = new Chromosome(123, 13, 55, 44, 33, 45);
        expected[1] = new Chromosome(154, 33, 65, 44, 21, 76);
        Chromosome[] result = ((AnularCrossOver)anularCrossOver).cross(p1, p2, 2, 0);
        Assert.assertArrayEquals(expected, result);
    }


    @Test
    public void simpleCrossTest2(){
        Chromosome[] expected = new Chromosome[2];
        expected[0] = new Chromosome(154, 33, 55, 44, 21, 76);
        expected[1] = new Chromosome(123, 13, 65, 44, 33, 45);
        Chromosome[] result = ((AnularCrossOver)anularCrossOver).cross(p1, p2, 4, 3);
        Assert.assertArrayEquals(expected, result);
    }
}
