package list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

public class SimpleQueueTest {

    private SimpleQueue<Integer> queue;

    @Before
    public void beforeTest() {
        queue = new SimpleQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(queue.size(), is(3));
    }

    @Test
    public void pollTest() {
        assertThat(queue.poll(), is(1));
        assertThat(queue.size(), is(2));
        assertThat(queue.poll(), is(2));
        assertThat(queue.size(), is(1));
        assertThat(queue.isEmpty(), is(false));
        assertThat(queue.poll(), is(3));
        assertThat(queue.isEmpty(), is(true));
        assertThat(queue.size(), is(0));
        assertNull(queue.poll());
    }

    @Test
    public void iteratorTest() {
        Iterator<Integer> iterator = queue.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));

        Iterator<Integer> it = queue.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void failFastTest() {
        Iterator<Integer> iterator = queue.iterator();
        queue.add(8);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceptionTest() {
        Iterator<Integer> iterator = queue.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }
}