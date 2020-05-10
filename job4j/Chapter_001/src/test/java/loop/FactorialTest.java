package loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {

    @Test
    public void check5is120() {
        int result = Factorial.calc(5);
        assertThat(result, is(120));
    }

    @Test
    public void check0is1() {
        int result = Factorial.calc(0);
        assertThat(result, is(1));
    }

    @Test
    public void check1is1() {
        int result = Factorial.calc(1);
        assertThat(result, is(1));
    }

    @Test
    public void check2is2() {
        int result = Factorial.calc(2);
        assertThat(result, is(2));
    }

    @Test
    public void check8is40320() {
        int result = Factorial.calc(8);
        assertThat(result, is(40320));
    }
}
