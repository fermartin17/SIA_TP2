package TP.Models.Genetics.crossOver;

import TP.Models.Genetics.Chromosome;
import org.junit.Assert;
import org.junit.Test;

public class ChromosomeTest {

    @Test
    public void equalsTest(){
        Chromosome c1 = new Chromosome(123, 13, 55, 44, 33,45);
        Chromosome c2 = new Chromosome(123, 13, 55, 44, 33,45);
        Assert.assertEquals(c1, c2);
    }


    @Test
    public void equalsTest2(){
        Chromosome c1 = new Chromosome(123, 13, 55, 44, 33,45);
        Chromosome c2 = new Chromosome(123, 13, 56, 44, 33,45);
        Assert.assertNotEquals(c1, c2);
    }

    @Test
    public void creationTest(){
        Chromosome original = new Chromosome(123, 13, 55, 44, 33,45);
        Chromosome created = new Chromosome(original);
        Assert.assertEquals(original, created);
    }

    @Test
    public void creationTestInaltered(){
        Chromosome original = new Chromosome(123, 13, 55, 44, 33,45);
        Chromosome created = new Chromosome(original);
        original.getChromosome()[2] = 8;
        Assert.assertNotEquals(original, created);
    }

}
