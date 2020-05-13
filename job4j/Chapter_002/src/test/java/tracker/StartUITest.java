package tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {
    PrintStream stdout = System.out;
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenUserAddItemThenOutputIs() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append("Menu.\n")
                                .append("0. Add new Item\n")
                                .append("1. Show all Items\n")
                                .append("2. Edit Item\n")
                                .append("3. Delete Item\n")
                                .append("4. Find Item by Id\n")
                                .append("5. Find Item by Name\n")
                                .append("6. Exit program\n")
                                .append("Select: 0\n")
                                .append("==== Create a new Item ====\n")
                                .append("Enter name of new Item: test name\n")
                                .append("Item test name added.\n")
                                .append("Menu.\n")
                                .append("0. Add new Item\n")
                                .append("1. Show all Items\n")
                                .append("2. Edit Item\n")
                                .append("3. Delete Item\n")
                                .append("4. Find Item by Id\n")
                                .append("5. Find Item by Name\n")
                                .append("6. Exit program\n")
                                .append("Select: 6\n")
                                .append("==== Exit ====\n")
                                .toString()
                )
        );
    }

    @Test
    public void whenNotNumberThenException() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"D"});
        new StartUI(input, tracker).init();
        assertThat(this.out.toString(),
                is(
                        new StringBuilder()
                                .append("Menu.\n")
                                .append("0. Add new Item\n")
                                .append("1. Show all Items\n")
                                .append("2. Edit Item\n")
                                .append("3. Delete Item\n")
                                .append("4. Find Item by Id\n")
                                .append("5. Find Item by Name\n")
                                .append("6. Exit program\n")
                                .append("Select: D\n")
                                .append("Not an Integer Number.\n")
                                .append("==== Exit ====\n")
                                .toString()
                )
        );
    }

    @Test
    public void whenOutOfRangeThenException() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"19"});
        new StartUI(input, tracker).init();
        assertThat(this.out.toString(),
                is(
                        new StringBuilder()
                                .append("Menu.\n")
                                .append("0. Add new Item\n")
                                .append("1. Show all Items\n")
                                .append("2. Edit Item\n")
                                .append("3. Delete Item\n")
                                .append("4. Find Item by Id\n")
                                .append("5. Find Item by Name\n")
                                .append("6. Exit program\n")
                                .append("Select: 19\n")
                                .append("Out of Menu Range.\n")
                                .append("==== Exit ====\n")
                                .toString()
                )
        );
    }
}
