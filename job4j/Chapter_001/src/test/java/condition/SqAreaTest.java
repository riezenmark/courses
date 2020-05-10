package condition;

import org.junit.Assert;
import org.junit.Test;

public class SqAreaTest {

    @Test
    public void whenp4k1s1() {
        int k = 1;
        int p = 4;
        double expected = 1;
        double delta = 0.01;
        double out = SqArea.square(p, k);
        Assert.assertEquals(expected, out, delta);
    }

    @Test
    public void whenp6k2s2() {
        int p = 6;
        int k = 2;
        double expected = 2;
        double delta = 0.01;
        double out = SqArea.square(p, k);
        Assert.assertEquals(expected, out, delta);
    }
}
