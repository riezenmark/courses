package tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMemoryOutput() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSystemOutput() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        input.ask("Enter: ", new int[] {1});
        assertThat(
                this.mem.toString(),
                is(
                        "Enter: invalid\n"
                                + "Invalid Input. This is not an Integer Number. Please, try Again.\n"
                                + "Enter: 1\n"
                )
        );
    }

    @Test
    public void whenOutOfRange() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"9", "0"})
        );
        input.ask("Enter: ", new int[]{0});
        assertThat(
                this.mem.toString(),
                is(
                        "Enter: 9\n"
                                + "This is not a Key from Menu. Please,"
                                + " input an Integer Number in range from 0 to 0\n"
                                + "Enter: 0\n"

                )
        );
    }
}
