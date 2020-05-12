package condition;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TriangleTest {
    @Test
    public void whenExist() {
        Point a = new Point(1, 0);
        Point b = new Point(3, 0);
        Point c = new Point(3, 2);
        Triangle abc = new Triangle(a, b, c);
        double result = abc.area();
        double delta = 0.001;
        assertEquals(result, 2, delta);
    }

    @Test
    public void whenNotExist() {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        Point c = new Point(5, 6);
        Triangle abc = new Triangle(a, b, c);
        double result = abc.area();
        double delta = 0.001;
        assertEquals(result, -1, delta);
    }
}
