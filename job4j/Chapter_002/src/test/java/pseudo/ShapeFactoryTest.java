package pseudo;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShapeFactoryTest {
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdOut);
    }

    @Test
    public void whenCreateSquareThenCreateTriangleAndDraw() {
        Shape square = ShapeFactory.create("Square");
        Shape triangle = ShapeFactory.create("Triangle");
        Paint painter = new Paint();
        painter.draw(square);
        painter.draw(triangle);
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("+------+")
                                .append("|      |")
                                .append("|      |")
                                .append("+------+")
                                .append("   +   ")
                                .append("  + +  ")
                                .append(" +   + ")
                                .append("+++++++")
                                .toString()
                )
        );
    }
}
