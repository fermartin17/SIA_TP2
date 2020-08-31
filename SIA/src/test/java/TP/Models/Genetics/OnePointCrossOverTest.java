package TP.Models.Genetics;

import TP.Interfaces.ICrossover;
import TP.Models.Genetics.Crossovers.OnePointCrossOver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OnePointCrossOverTest {

    private Chromosome p1, p2;

    private ICrossover onePointCrossOver;

    @Before
    public void setup(){
        p1 = new Chromosome(123, 13, 55, 44, 33,45);
        p2 = new Chromosome(154, 33, 65, 44, 21,76);
        onePointCrossOver = new OnePointCrossOver();
    }

    @Test
    public void nullTest(){
        Assert.assertNull(onePointCrossOver.cross(p1, null));
        Assert.assertNull(onePointCrossOver.cross(null, p2));
        Assert.assertNull(onePointCrossOver.cross(null, null));
    }

    @Test
    public void simpleCrossTest(){
        Chromosome[] expected = new Chromosome[2];
        expected[0] = new Chromosome(123, 13, 65, 44, 21, 76);
        expected[1] = new Chromosome(154, 33, 55, 44, 33, 45);
        Chromosome[] result = ((OnePointCrossOver)onePointCrossOver).cross(p1, p2, 2);
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void simpleCrossTest2(){
        Chromosome[] expected = new Chromosome[2];
        expected[0] = new Chromosome(123, 13, 55, 44, 33, 76);
        expected[1] = new Chromosome(154, 33, 65, 44, 21, 45);
        Chromosome[] result = ((OnePointCrossOver)onePointCrossOver).cross(p1, p2, 5);
        Assert.assertArrayEquals(expected, result);
    }
}
