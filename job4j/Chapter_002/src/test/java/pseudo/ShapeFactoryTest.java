package pseudo;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import pseudo.factories.*;
import pseudo.shape.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShapeFactoryTest {
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private ShapeFactory factory;

    private TestApplication configureApplication(String name) {
        if (name.equals("Square")) {
            factory = new SquareFactory();
        } else if (name.equals("Triangle")) {
            factory = new TriangleFactory();
        }
        return new TestApplication(factory, 3, 5);
    }

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdOut);
    }

    @Test
    public void whenCreateSquare() {
        configureApplication("Square").start();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("null\n")

                                .append("++++++++")
                                .append("+      +")
                                .append("+      +")
                                .append("++++++++\n")

                                .append("++++++++")
                                .append("++++++++")
                                .append("++++++++")
                                .append("++++++++\n")

                                .append("It is a Filled Shape.\n")
                                .toString()
                )
        );
    }

    @Test
    public void whenCreateTriangle() {
        configureApplication("Triangle").start();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("null\n")

                                .append("   +   ")
                                .append("  + +  ")
                                .append(" +   + ")
                                .append("+++++++\n")

                                .append("   +   ")
                                .append("  +++  ")
                                .append(" +++++ ")
                                .append("+++++++\n")

                                .append("It is a Filled Shape.\n")
                                .toString()
                )
        );
    }

    private static class TestApplication {
        private final Shape emptyShape;
        private final Shape shape;
        private final FilledShape filledShape;

        public TestApplication(ShapeFactory factory, int sizeOfShape,
                               int sizeOfFilledShape) {
            emptyShape = factory.createEmpty();
            shape = factory.create(sizeOfShape);
            filledShape = factory.createFilled(sizeOfFilledShape);
        }

        public void start() {
            Paint painter = new Paint();
            painter.draw(emptyShape, shape, filledShape);
            System.out.println(filledShape.isFilled());
        }
    }

}
