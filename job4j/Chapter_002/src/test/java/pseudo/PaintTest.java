package pseudo;

import org.junit.Test;
import java.io.ByteArrayOutputStream;        //input/output, array of Bytes
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {

    @Test
    public void whenDrawSquare() {
        //get link on standard output stream
        PrintStream stdOut = System.out;
        //array of outputting bytes
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //change screen output on memory output
        System.setOut(new PrintStream(out));
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
        //return standard output stream
        System.setOut(stdOut);
    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
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
        System.setOut(stdOut);
    }
}
