package condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SqMaxTest {

    @Test
    public void whenFirst() {
        int result = SqMax.max(4, 1, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenSecond() {
        int result = SqMax.max(1, 4, 2, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenThird() {
        int result = SqMax.max(2, 1, 4, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenFourth() {
        int result = SqMax.max(3, 1, 2, 4);
        assertThat(result, is(4));
    }

    @Test
    public void whenEquals() {
        int result = SqMax.max(3, 3, 3, 3);
        assertThat(result, is(3));
    }
}
