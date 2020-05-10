package loop;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FitnessTest {
    @Test
    public void whenIvanGreaterThanNik() {
        Fitness fit = new Fitness();
        int result = fit.calc(95, 90);
        assertThat(result, is(0));
    }

    @Test
    public void whenIvanLessThanNikByOne() {
        Fitness fit = new Fitness();
        int result = fit.calc(90, 95);
        assertThat(result, is(1));
    }

    @Test
    public void whenIvanLessThanNikByTwo() {
        Fitness fit = new Fitness();
        int result = fit.calc(50, 90);
        assertThat(result, is(2));
    }
}

