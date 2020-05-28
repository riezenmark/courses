package tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Before
    public void startFirst() {
        whenAddNewItemThenTrackerHasSameItem();
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = Tracker.getInstance();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = Tracker.getInstance();
        Item first = new Item("first");
        tracker.add(first);
        Item second = new Item("second");
        tracker.add(second);
        assertThat(tracker.getAll().get(1).getName(), is("first"));
    }

    @Test
    public void whenGetAll() {
        Tracker tracker = Tracker.getInstance();
        assertThat(tracker.getAll().get(2).getName(), is("second"));
    }

    @After
    public void startLatest() {
        whenGetAll();
    }
}
