package pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ThreadPoolTest {
    private final ThreadPool threadPool = new ThreadPool();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream std = System.out;

    @Before
    public void setOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void setStdOut() {
        System.setOut(std);
    }

    @Test
    public void threeTasks() throws InterruptedException {
        threadPool.work(
                () -> System.out.println("First task.")
        );
        Thread.sleep(50);
        threadPool.work(
                () -> System.out.println("Second task.")
        );
        Thread.sleep(50);
        threadPool.work(
                () -> System.out.println("Third task.")
        );
        Thread.sleep(50);
        threadPool.shutdown();
        assertThat(
                out.toString(),
                is(
                        "First task." + System.lineSeparator()
                                + "Second task." + System.lineSeparator()
                                + "Third task." + System.lineSeparator()
                )
        );
    }
}