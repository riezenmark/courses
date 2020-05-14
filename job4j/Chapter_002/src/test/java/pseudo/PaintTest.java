package pseudo;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.ByteArrayOutputStream;        //input/output, array of Bytes
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing the class Paint
 */
public class PaintTest {
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Sets output stream to memory as array of Bytes
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Sets standard output stream
     */
    @After
    public void backOutput() {
        System.setOut(this.stdOut);
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("+------+")
                                .append("|      |")
                                .append("|      |")
                                .append("+------+")
                                .toString()
                )
        );
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("   +   ")
                                .append("  + +  ")
                                .append(" +   + ")
                                .append("+++++++")
                                .toString()
                )
        );
    }
}
