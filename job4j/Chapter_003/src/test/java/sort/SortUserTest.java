package sort;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortUserTest {
    SortUser sortUser = new SortUser();

    @Test
    public void sortTest() {
        List<User> users = List.of(
                new User("T", 5),
                new User("O", 2),
                new User("R", 4),
                new User("S", 1)
        );

        Set<User> expected = Set.of(
                new User("S", 1),
                new User("O", 2),
                new User("R", 4),
                new User("T", 5)
        );

        assertThat(sortUser.sort(users), is(expected));
    }

    @Test
    public void sortByNameLengthTest() {

        User user3 = new User("SOR", 5);
        User user1 = new User("S", 2);
        User user4 = new User("SORT", 4);
        User user2 = new User("SO", 1);

        ArrayList<User> users = new ArrayList<>(List.of(user3, user1, user4, user2));

        List<User> expected = List.of(user1, user2, user3, user4);

        assertThat(sortUser.sortByNameLength(users), is(expected));
    }

    @Test
    public void sortByAllFieldsTest() {
        User user1 = new User("Sergei", 25);
        User user2 = new User("Ivan", 30);
        User user3 = new User("Sergei", 20);
        User user4 = new User("Ivan", 25);

        ArrayList<User> users = new ArrayList<>(List.of(user1, user2, user3, user4));

        List<User> expected = List.of(user4, user2, user3, user1);

        assertThat(sortUser.sortByAllFields(users), is(expected));
    }
}
