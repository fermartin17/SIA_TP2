package TP.Models.Genetics;

import TP.Interfaces.ICrossover;
import TP.Models.Genetics.Crossovers.UniformCrossOver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UniformCrossOverTest {

    private Chromosome p1, p2;

    private ICrossover uniformCrossOver;

    @Before
    public void setup(){
        p1 = new Chromosome(123, 13, 55, 44, 33,45);
        p2 = new Chromosome(154, 33, 65, 44, 21,76);
        uniformCrossOver = new UniformCrossOver();
    }

    @Test
    public void nullTest(){
        Assert.assertNull(uniformCrossOver.cross(p1, null));
        Assert.assertNull(uniformCrossOver.cross(null, p2));
        Assert.assertNull(uniformCrossOver.cross(null, null));
        Assert.assertNull(((UniformCrossOver)uniformCrossOver).cross(p1, p2, null));
    }

    @Test
    public void unalteredTest(){
        Chromosome[] expected = new Chromosome[2];
        expected[0] = new Chromosome(123, 13, 55, 44, 33,45);
        expected[1] = new Chromosome(154, 33, 65, 44, 21,76);
        Boolean[] randoms = new Boolean[] {true,true,true,true,true,true};
        Chromosome[] result = ((UniformCrossOver)uniformCrossOver).cross(p1, p2, randoms);
        Assert.assertArrayEquals(expected, result);
    }


    @Test
    public void simpleCrossTest2(){
        Chromosome[] expected = new Chromosome[2];
        expected[0] = new Chromosome(123, 33, 55, 44, 21,45);
        expected[1] = new Chromosome(154, 13, 65, 44, 33,76);
        Boolean[] randoms = new Boolean[] {true,false,true,true,false,true};
        Chromosome[] result = ((UniformCrossOver)uniformCrossOver).cross(p1, p2, randoms);
        Assert.assertArrayEquals(expected, result);
    }
}
