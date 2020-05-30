package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DiapasonFunctionTest {
    DiapasonFunction function = new DiapasonFunction();

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSquareFunctionThenSquareResults() {
        List<Double> result = function.diapason(3, 6, x -> x * x - 3);
        List<Double> expected = Arrays.asList(6D, 13D, 22D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        List<Double> result = function.diapason(8, 12, Math::log10);
        List<Double> expected = Arrays.asList(Math.log10(8), Math.log10(9), Math.log10(10), Math.log10(11));
        assertThat(result, is(expected));
    }

}
