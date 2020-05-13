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
    public void whenOne() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[] {action});
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenTwoTrue() {
        StubInput input = new StubInput(
                new String[] {"1"}
        );
        StubAction action1 = new StubAction();
        StubAction action2 = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[] {action1, action2});
        assertThat(action2.isCall(), is(true));
    }

    @Test
    public void whenTwoFalse() {
        StubInput input = new StubInput(
                new String[] {"1"}
        );
        StubAction action1 = new StubAction();
        StubAction action2 = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[] {action1, action2});
        assertThat(action1.isCall(), is(false));
    }

    @Test
    public void whenTwoBothTrue() {
        StubInput input = new StubInput(
                new String[] {"0", "1"}
        );
        StubAction action1 = new StubAction();
        StubAction action2 = new StubAction();
        new StartUI().init(input, new Tracker(), new UserAction[] {action1, action2});
        assertThat(action1.isCall(), is(true));
        assertThat(action2.isCall(), is(true));
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "2"});
        UserAction[] actions = {
                new CreateAction("Add a new Item"),
                new StubAction(),
                new StubAction()
        };
        new StartUI().init(input, tracker, actions);
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenUserAddItemThenOutputIs() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "2"});
        UserAction[] actions = {
                new CreateAction("Add a new Item"),
                new StubAction(),
                new StubAction()
        };
        new StartUI().init(input, tracker, actions);
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append("Menu.\n")
                                .append("0. Add a new Item\n")
                                .append("1. Stub action\n")
                                .append("2. Stub action\n")
                                .append("Select: 0\n")
                                .append("==== Create a new Item ====\n")
                                .append("Enter name of new Item: test name\n")
                                .append("Item test name added.\n")
                                .append("Menu.\n")
                                .append("0. Add a new Item\n")
                                .append("1. Stub action\n")
                                .append("2. Stub action\n")
                                .append("Select: 2\n")
                                .toString()
                )
        );
    }
}
