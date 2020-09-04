package TP.Models.CutCriteria;

import TP.Models.Generation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumberOfGenerationsCriteriaTest {

    BaseCutCriteria cutCriteria;

    @Before
    public void setup(){
        cutCriteria = new NumberOfGenerationsCriteria(5);
    }

    @Test
    public void numberOfGenerationsTest(){
        Generation g = new Generation();
        Assert.assertFalse(cutCriteria.cutProgram(g));
        for(int i = 0; i < 4; i++){
            g.nextGeneration();
            Assert.assertFalse(cutCriteria.cutProgram(g));
        }
        g.nextGeneration();
        Assert.assertTrue(cutCriteria.cutProgram(g));
    }
}
