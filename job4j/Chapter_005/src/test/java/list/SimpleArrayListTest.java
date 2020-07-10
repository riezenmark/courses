package list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

public class SimpleArrayListTest {

    @Test
    public void defaultConstructorTest() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        assertThat(list.size(), is(0));
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void lengthConstructorTest() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(3);
        assertThat(list.size(), is(0));
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void arrayConstructorAndGetTest() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(new Integer[] {3, 5, 7});
        assertThat(list.size(), is(3));
        assertThat(list.isEmpty(), is(false));
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(5));
        assertThat(list.get(2), is(7));
    }

    @Test
    public void addTest() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();

        list.add(3);
        list.add(5);
        list.add(7);

        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(5));
        assertThat(list.get(2), is(7));
        assertNull(list.get(8));

        list.add(1, 4);
        assertThat(list.size(), is(4));
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(4));
        assertThat(list.get(2), is(5));
        assertThat(list.get(3), is(7));

        list.add(-1, 8);
        assertThat(list.size(), is(4));
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(4));
        assertThat(list.get(2), is(5));
        assertThat(list.get(3), is(7));

        list.add(184, 8);
        assertThat(list.size(), is(5));
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(4));
        assertThat(list.get(2), is(5));
        assertThat(list.get(3), is(7));
        assertThat(list.get(4), is(8));

        list.add(3, 8);
        assertThat(list.size(), is(6));
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(4));
        assertThat(list.get(2), is(5));
        assertThat(list.get(3), is(8));
        assertThat(list.get(4), is(7));
        assertThat(list.get(5), is(8));

        list.add(5, 9);
        assertThat(list.size(), is(7));
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(4));
        assertThat(list.get(2), is(5));
        assertThat(list.get(3), is(8));
        assertThat(list.get(4), is(7));
        assertThat(list.get(5), is(9));
        assertThat(list.get(6), is(8));

        list.add(0, 9);
        assertThat(list.size(), is(8));
        assertThat(list.get(0), is(9));
        assertThat(list.get(1), is(3));
        assertThat(list.get(2), is(4));
        assertThat(list.get(3), is(5));
        assertThat(list.get(4), is(8));
        assertThat(list.get(5), is(7));
        assertThat(list.get(6), is(9));
        assertThat(list.get(7), is(8));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getExceptionTest() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        list.get(42);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void failFastTest() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(new Integer[] {3, 5, 7});

        Iterator<Integer> it = list.iterator();
        list.add(8);
        it.next();
    }

    @Test
    public void iteratorTest() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(new Integer[] {3, 5, 7});
        Iterator<Integer> it = list.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorNextExceptionTest() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>(new Integer[] {3, 5, 7});
        Iterator<Integer> it = list.iterator();

        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test
    public void genericTest() {
        SimpleArrayList<String> list = new SimpleArrayList<>(new String[] {"test1", "test2", "test3"});
        assertThat(list.size(), is(3));
        assertThat(list.get(0), is("test1"));
        assertThat(list.get(1), is("test2"));
        assertThat(list.get(2), is("test3"));
    }
}
