package generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;
import static org.hamcrest.core.Is.is;

public class SimpleArrayTest {

    @Test
    public void whenIntAddWorks() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 3);

        array.add(1);
        array.add(2);
        array.add(3);

        assertThat(array.get(2), is(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenIntAddDoesNotWork() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 3);

        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
    }


    @Test
    public void whenIntSetWorks() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 3);

        array.add(1);
        array.add(2);
        array.add(3);
        array.set(1, 5);

        assertThat(array.get(1), is(5));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenIntSetDoesNotWork() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 3);

        array.add(1);
        array.add(2);
        array.add(3);
        array.set(5, 3);
    }

    @Test
    public void whenIntRemoveWorks() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 3);

        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(1);

        assertThat(array.get(1), is(3));
        assertNull(array.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenIntRemoveDoesNotWork() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 3);

        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(7);
    }

    @Test
    public void whenIntIteratorWorks() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 3);
        Iterator<Integer> it = array.iterator();

        array.add(1);
        array.add(2);
        array.add(3);

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIntIteratorDoesNotWork() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 3);
        Iterator<Integer> it = array.iterator();

        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test
    public void whenStringAddWorks() {
        SimpleArray<String> array = new SimpleArray<>(String.class, 3);

        array.add("test1");
        array.add("test2");
        array.add("test3");

        assertThat(array.get(2), is("test3"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenStringAddDoesNotWork() {
        SimpleArray<String> array = new SimpleArray<>(String.class, 3);

        array.add("test1");
        array.add("test2");
        array.add("test3");
        array.add("test4");
    }


    @Test
    public void whenStringSetWorks() {
        SimpleArray<String> array = new SimpleArray<>(String.class, 3);

        array.add("test1");
        array.add("test2");
        array.add("test3");
        array.set(1, "test4");

        assertThat(array.get(1), is("test4"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenStringSetDoesNotWork() {
        SimpleArray<String> array = new SimpleArray<>(String.class, 3);

        array.add("test1");
        array.add("test2");
        array.add("test3");
        array.set(10, "test4");
    }

    @Test
    public void whenStringRemoveWorks() {
        SimpleArray<String> array = new SimpleArray<>(String.class, 3);

        array.add("test1");
        array.add("test2");
        array.add("test3");
        array.remove(1);

        assertThat(array.get(1), is("test3"));
        assertNull(array.get(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenStringRemoveDoesNotWork() {
        SimpleArray<String> array = new SimpleArray<>(String.class, 3);

        array.add("test1");
        array.add("test2");
        array.add("test3");
        array.remove(19);
    }

    @Test
    public void whenStringIteratorWorks() {
        SimpleArray<String> array = new SimpleArray<>(String.class, 3);
        Iterator<String> it = array.iterator();

        array.add("test1");
        array.add("test2");
        array.add("test3");

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test2"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test3"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenStringIteratorDoesNotWork() {
        SimpleArray<String> array = new SimpleArray<>(String.class, 3);
        Iterator<String> it = array.iterator();

        it.next();
        it.next();
        it.next();
        it.next();
    }
}