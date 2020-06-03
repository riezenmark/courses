package map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserTest {
    @Test
    public void mapTest() {
        User user1 = new User(
                "test",
                3,
                new GregorianCalendar(
                        1984,
                        Calendar.MARCH,
                        3
                )
        );

        User user2 = new User(
                "test",
                3,
                new GregorianCalendar(
                        1984,
                        Calendar.MARCH,
                        3
                )
        );

        Map<User, Object> map = new HashMap<>();

        map.put(user1, 1);
        map.put(user2, 2);

        assertThat(map.toString().matches("(.)map.User@[a-f0-9]{8}=1, map.User@[a-z0-9]{8}=2(.)"), is(true));
    }
}