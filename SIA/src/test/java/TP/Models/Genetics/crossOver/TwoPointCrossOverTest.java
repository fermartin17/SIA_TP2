package TP.Models.Genetics.crossOver;

import TP.Interfaces.ICrossover;
import TP.Models.Genetics.Chromosome;
import TP.Models.Genetics.Crossovers.TwoPointsCrossOver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwoPointCrossOverTest {

    private Chromosome p1, p2;

    private ICrossover twoPointsCrossOver;

    @Before
    public void setup(){
        p1 = new Chromosome(123, 13, 55, 44, 33,45);
        p2 = new Chromosome(154, 33, 65, 44, 21,76);
        twoPointsCrossOver = new TwoPointsCrossOver();
    }

    @Test
    public void nullTest(){
        Assert.assertNull(twoPointsCrossOver.cross(p1, null));
        Assert.assertNull(twoPointsCrossOver.cross(null, p2));
        Assert.assertNull(twoPointsCrossOver.cross(null, null));
        Assert.assertNull(((TwoPointsCrossOver)twoPointsCrossOver).cross(p1, p2, 2, 1));
    }

    @Test
    public void unalteredTest(){
        Chromosome[] expected = new Chromosome[2];
        expected[0] = new Chromosome(123, 13, 55, 44, 33, 45);
        expected[1] = new Chromosome(154, 33, 65, 44, 21, 76);
        Chromosome[] result = ((TwoPointsCrossOver)twoPointsCrossOver).cross(p1, p2, 2, 2);
        Assert.assertArrayEquals(expected, result);
    }


    @Test
    public void simpleCrossTest2(){
        Chromosome[] expected = new Chromosome[2];
        expected[0] = new Chromosome(123, 13, 65, 44, 33, 45);
        expected[1] = new Chromosome(154, 33, 55, 44, 21, 76);
        Chromosome[] result = ((TwoPointsCrossOver)twoPointsCrossOver).cross(p1, p2, 2, 4);
        Assert.assertArrayEquals(expected, result);
    }
}
