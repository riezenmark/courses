package condition;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void distance() {
        int x1 = 0;
        int y1 = 0;
        int x2 = 3;
        int y2 = 4;
        double expected = 5;
        double delta = 0.01;
        double out = Point.distance(x1, y1, x2, y2);

        Assert.assertEquals(expected, out, delta);
    }
}
