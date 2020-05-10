package array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MinDiapasonTest {

    @Test
    public void whenMinIs0() {
        int[] input = new int[]{2, 10, 3};
        int result = MinDiapason.findMin(input, 1, 2);
        int expect = 3;
        assertThat(result, is(expect));
    }

    @Test
    public void whenMinIs1() {
        int[] input = new int[]{2, 10, 3, 1, 8};
        int result = MinDiapason.findMin(input, 1, 4);
        int expect = 1;
        assertThat(result, is(expect));
    }
}
