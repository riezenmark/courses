package condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {

    @Test
    public void whenTwo() {
        assertThat(Max.max(4, 6), is(6));
    }

    @Test
    public void whenThree() {
        assertThat(Max.max(10, 14, 2), is(14));
    }
}
