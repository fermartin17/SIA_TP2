package TP.Models.CutCriteria;

import TP.Interfaces.ICutCriteria;
import TP.Models.Generation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeCriteriaTest {

    ICutCriteria cutCriteria;

    @Before
    public void setup(){
        cutCriteria = new TimeCriteria(TimeCriteria.secondsToMillis(10));
    }

    @Test
    public void timeCutTest(){
        //start timer
        Generation g = new Generation();
        cutCriteria.cutProgram(g);
        Assert.assertFalse(cutCriteria.cutProgram(g));
        try {
            Thread.sleep((long) (5.0 * 1000));
            Assert.assertFalse(cutCriteria.cutProgram(g));
            Thread.sleep((long) (5.0 * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(cutCriteria.cutProgram(g));
    }
}
