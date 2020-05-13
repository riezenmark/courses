package tracker;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUITest {
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

}
