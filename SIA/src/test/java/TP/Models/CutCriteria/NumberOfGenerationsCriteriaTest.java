package TP.Models.CutCriteria;

import TP.Interfaces.ICutCriteria;
import TP.Models.Generation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumberOfGenerationsCriteriaTest {

    ICutCriteria cutCriteria;

    @Before
    public void setup(){
        cutCriteria = new NumberOfGenerationsCriteria(5);
    }

    @Test
    public void numberOfGenerationsTest(){
        Generation g = new Generation();
        Assert.assertFalse(cutCriteria.cutProgram(g));
        for(int i = 0; i < 4; i++){
            g.nextGeneration(g.getCurrentPopulation());
            Assert.assertFalse(cutCriteria.cutProgram(g));
        }
        g.nextGeneration(g.getCurrentPopulation());
        Assert.assertTrue(cutCriteria.cutProgram(g));
    }
}
