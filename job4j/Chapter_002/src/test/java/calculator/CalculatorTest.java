package calculator;

import org.junit.Test;
import org.junit.Assert;

public class CalculatorTest {

    @Test
    public void whenTwo() {
        Assert.assertEquals(Calculator.add(4, 6), 10, 0.01);
    }

    @Test
    public void whenThree() {
        Assert.assertEquals(Calculator.add(10, 14, 2), 26, 0.01);
    }
}
