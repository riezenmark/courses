package calculator;

import org.junit.Assert;
import org.junit.Test;

public class FitTest {

    @Test
    public void manWeight() {
        double in = 180;
        double expected = 92;
        double delta = 0.01;
        double out = Fit.manWeight(in);
        Assert.assertEquals(expected, out, delta);
    }

    @Test
    public void womanWeight() {
        double in = 175;
        double expected = 74.75;
        double delta = 0.01;
        double out = Fit.womanWeight(in);
        Assert.assertEquals(expected, out, delta);
    }
}
