package tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

public class SimpleTreeTest {
    @Test
    public void addTest() {
        SimpleTree<Integer> tree = new SimpleTree<>();
        assertThat(tree.size(), is(0));
        assertThat(tree.isEmpty(), is(true));
        assertThat(tree.add(4), is(true));
        assertThat(tree.size(), is(1));
        assertThat(tree.isEmpty(), is(false));
        assertThat(tree.add(-8), is(true));
        assertThat(tree.size(), is(2));
        assertThat(tree.isEmpty(), is(false));
        assertThat(tree.add(2), is(true));
        assertThat(tree.size(), is(3));
        assertThat(tree.isEmpty(), is(false));
        assertThat(tree.add(2), is(false));
        assertThat(tree.size(), is(3));
        assertThat(tree.isEmpty(), is(false));
    }

    @Test
    public void getTest() {
        SimpleTree<Integer> tree = new SimpleTree<>();
        tree.add(4);
        tree.add(8);
        tree.add(5);
        tree.add(3);
        tree.add(8);
        List<Integer> list = tree.get();
        assertThat(list.size(), is(4));
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(4));
        assertThat(list.get(2), is(5));
        assertThat(list.get(3), is(8));
    }

    @Test
    public void findTest() {
        SimpleTree<Integer> tree = new SimpleTree<>();
        tree.add(4);
        tree.add(8);
        tree.add(5);
        tree.add(3);

        assertThat(tree.find(8).getElement(), is(8));
        assertNull(tree.find(64));
    }

    @Test
    public void iteratorTest() {
        SimpleTree<Integer> tree = new SimpleTree<>();
        tree.add(4);
        tree.add(8);
        tree.add(5);
        tree.add(3);
        Iterator<Integer> iterator = tree.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(8));
        assertThat(iterator.hasNext(), is(false));
    }
}
