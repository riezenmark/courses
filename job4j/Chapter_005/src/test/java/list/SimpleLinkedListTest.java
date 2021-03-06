package list;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
        assertNull(list.get(42));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.size(), is(3));
    }

    @Test
    public void deleteTest() {
        assertThat(list.delete(), is(3));
        assertThat(list.size(), is(2));
        assertThat(list.delete(), is(2));
        assertThat(list.size(), is(1));
        assertThat(list.isEmpty(), is(false));
        assertThat(list.delete(), is(1));
        assertThat(list.isEmpty(), is(true));
        assertThat(list.size(), is(0));
        assertNull(list.delete());
    }

    @Test
    public void iteratorTest() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));

        Iterator<Integer> it = list.iterator();
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
        Iterator<Integer> iterator = list.iterator();
        list.add(8);
        iterator.next();
    }

    @Test
    public void hasCycleTest() {
        SimpleLinkedList<Integer> sl = new SimpleLinkedList<>();
        sl.add(1);
        sl.add(2);
        sl.add(3);
        assertThat(sl.hasCycle(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorExceptionTest() {
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test
    public void clearTest() {
        list.clear();
        assertThat(list.isEmpty(), is(true));
        assertThat(list.size(), is(0));
        assertNull(list.delete());
        assertNull(list.get(42));
    }

    @Test
    public void deleteNumberTest() {
        assertThat(list.delete(1), is(2));
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(3));
        assertThat(list.delete(), is(3));
        assertThat(list.size(), is(1));
        assertThat(list.get(0), is(1));
        list.add(2);
        list.add(3);
        assertThat(list.delete(0), is(1));
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(3));
        assertNull(list.delete(8));
        assertThat(list.delete(), is(3));
        assertThat(list.delete(), is(2));
    }

    @Test
    public void deleteValueTest() {
        SimpleLinkedList<String> linkedList = new SimpleLinkedList<>();

        linkedList.add("test1");
        linkedList.add("test2");
        linkedList.add("test3");

        assertThat(linkedList.size(), is(3));
        assertThat(linkedList.delete("test2"), is("test2"));
        assertThat(linkedList.size(), is(2));
        assertThat(linkedList.get(0), is("test1"));
        assertThat(linkedList.get(1), is("test3"));
    }
}
