package generics;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserConvertTest {
    @Test
    public void when6() {
        User user1 = new User(1, "A", "V");
        User user2 = new User(2, "B", "M");
        User user3 = new User(3, "C", "A");
        User user4 = new User(4, "D", "Kh");
        User user5 = new User(5, "E", "T");
        User user6 = new User(6, "F", "Ch");

        Map<Integer, User> expected = Map.of(
                1, user1,
                2, user2,
                3, user3,
                4, user4,
                5, user5,
                6, user6
        );

        HashMap<Integer, User> result = UserConvert.process(
                List.of(
                        user1,
                        user2,
                        user3,
                        user4,
                        user5,
                        user6
                )
        );
        assertThat(result, is(expected));
    }
}
