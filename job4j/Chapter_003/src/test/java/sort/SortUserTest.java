package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortUserTest {
    @Test
    public void when4() {
        ArrayList<User> users = new ArrayList<>(
                Arrays.asList(
                        new User("T", 5),
                        new User("O", 2),
                        new User("R", 4),
                        new User("S", 1)
                )
        );

        TreeSet<User> expected = new TreeSet<>(
                Arrays.asList(
                        new User("S", 1),
                        new User("O", 2),
                        new User("R", 4),
                        new User("T", 5)
                )
        );

        assertThat(SortUser.sort(users), is(expected));
    }
}
