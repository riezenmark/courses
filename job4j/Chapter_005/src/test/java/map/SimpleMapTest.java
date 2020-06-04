package map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

public class SimpleMapTest {
    private SimpleMap<Integer, String> map;

    @Before
    public void beforeTest() {
        map = new SimpleMap<>();
        map.put(2, "test2");
        map.put(3, "test3");
        map.put(1, "test1");
    }

    @Test
    public void getTest() {
        assertThat(map.get(2), is("test2"));
        assertNull(map.get(4));
    }

    @Test
    public void sizeTest() {
        assertThat(map.size(), is(3));
    }

    @Test
    public void deleteAndIsEmptyTest() {
        assertThat(map.isEmpty(), is(false));
        assertThat(map.size(), is(3));
        assertThat(map.delete(2), is(true));
        assertThat(map.size(), is(2));
        assertThat(map.delete(3), is(true));
        assertThat(map.size(), is(1));
        assertThat(map.isEmpty(), is(false));
        assertThat(map.delete(1), is(true));
        assertThat(map.size(), is(0));
        assertThat(map.isEmpty(), is(true));
        assertThat(map.delete(2), is(false));
        assertThat(map.delete(3), is(false));
        assertThat(map.delete(1), is(false));
    }

    @Test
    public void putTest() {
        assertThat(map.put(4, "test4"), is(true));
        assertThat(map.get(4), is("test4"));
        assertThat(map.get(2), is("test2"));
        assertThat(map.put(2, "2test"), is(true));
        assertThat(map.get(2), is("2test"));
    }

    @Test
    public void iteratorTest() {
        Iterator<String> iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test1"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test2"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test3"));
        assertThat(iterator.hasNext(), is(false));

        map.put(4, "test4");
        map.put(3, "3test");
        iterator = map.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test1"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test2"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("3test"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test4"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceptionTest() {
        Iterator<String> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test
    public void a() {
        Iterator<String> iterator = map.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void failFastTest() {
        Iterator<String> iterator = map.iterator();
        map.delete(1);
        iterator.next();
    }
}