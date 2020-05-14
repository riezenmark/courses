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
        Tracker tracker = Tracker.getInstance();
        Input input = new StubInput(new String[]{"0", "test name", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                this.out.toString(),
                is(
                        "Menu.\n"
                                + "0. Add new Item\n"
                                + "1. Show all Items\n"
                                + "2. Edit Item\n"
                                + "3. Delete Item\n"
                                + "4. Find Item by Id\n"
                                + "5. Find Item by Name\n"
                                + "6. Exit Program\n"
                                + "Select: 0\n"
                                + "==== Create a new Item ====\n"
                                + "Enter name of new Item: test name\n"
                                + "Item test name added.\n"
                                + "Menu.\n"
                                + "0. Add new Item\n"
                                + "1. Show all Items\n"
                                + "2. Edit Item\n"
                                + "3. Delete Item\n"
                                + "4. Find Item by Id\n"
                                + "5. Find Item by Name\n"
                                + "6. Exit Program\n"
                                + "Select: 6\n"
                                + "==== Exit ====\n"
                )
        );
    }
}
