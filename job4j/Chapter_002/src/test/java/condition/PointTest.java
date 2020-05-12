package condition;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PointTest {
    @Test
    public void distance2d(){
        Point a = new Point(3, 2);
        Point b = new Point(10, 2);
        double result = a.distance(b);
        double delta = 0.001;
        assertEquals(result, 7, delta);
    }

    @Test
    public void distance3d(){
        Point a = new Point(3, 2, 6);
        Point b = new Point(10, 2, 8);
        double result = a.distance3d(b);
        double delta = 0.001;
        assertEquals(result, 7.28, delta);
    }
}
