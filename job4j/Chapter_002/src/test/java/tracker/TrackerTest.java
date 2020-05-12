package tracker;

import org.junit.Test;
import org.junit.Assert;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        tracker.add(previous);
        Item next = new Item("test2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteThanNull() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        tracker.add(previous);
        Item next = new Item("test2");
        tracker.add(next);
        tracker.delete(previous.getId());
        Assert.assertNull(tracker.findById(previous.getId()));
    }

    @Test
    public void whenGetAll() {
        Tracker tracker = new Tracker();
        Item first = new Item("first");
        tracker.add(first);
        tracker.add(first);
        tracker.delete(first.getId());
        Item second = new Item("second");
        tracker.add(second);
        assertThat(tracker.getAll()[1].getName(), is("second"));
    }

    @Test
    public void whenFindByNameThanName() {
        Tracker tracker = new Tracker();
        Item first = new Item("first");
        tracker.add(first);
        Item second = new Item("second");
        tracker.add(second);
        assertThat(tracker.findByName("first").getName(), is("first"));
    }

    @Test
    public void whenFindByNameThanNull() {
        Tracker tracker = new Tracker();
        Item first = new Item("first");
        tracker.add(first);
        tracker.delete(first.getId());
        Item second = new Item("second");
        tracker.add(second);
        Assert.assertNull(tracker.findByName("first"));
    }
}
