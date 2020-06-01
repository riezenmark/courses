package list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

public class SimpleStackTest {

    private SimpleStack<Integer> stack;

    @Before
    public void beforeTest() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(stack.get(1), is(2));
        assertNull(stack.get(42));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(stack.size(), is(3));
    }

    @Test
    public void popTest() {
        assertThat(stack.pop(), is(3));
        assertThat(stack.size(), is(2));
        assertThat(stack.pop(), is(2));
        assertThat(stack.size(), is(1));
        assertThat(stack.pop(), is(1));
        assertThat(stack.size(), is(0));
        assertNull(stack.pop());
    }

    @Test
    public void iteratorTest() {
        Iterator<Integer> iterator = stack.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));

        Iterator<Integer> it = stack.iterator();
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
        Iterator<Integer> iterator = stack.iterator();
        stack.push(8);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceptionTest() {
        Iterator<Integer> iterator = stack.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }
}