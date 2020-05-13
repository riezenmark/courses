package tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Before
    public void whenAddNewItemThenTrackerHasSameItem() {
        TrackerSingleton tracker = TrackerSingleton.TRACKER;
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        TrackerSingleton tracker = TrackerSingleton.TRACKER;
        Item first = new Item("first");
        tracker.add(first);
        Item second = new Item("second");
        tracker.add(second);
        assertThat(tracker.getAll()[1].getName(), is("first"));
    }

    @After
    public void whenGetAll() {
        TrackerSingleton tracker = TrackerSingleton.TRACKER;
        assertThat(tracker.getAll()[2].getName(), is("second"));
    }
}
