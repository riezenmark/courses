package stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProfilesTest {
    @Test
    public void test() {
        Profiles p = new Profiles();
        List<Profile> profiles = new ArrayList<>(Arrays.asList(
                new Profile(new Profile.Address("City", "Street", 1, 2)),
                new Profile(new Profile.Address("Town", "Avenue", 3, 4)),
                new Profile(new Profile.Address("Village", "Prospect", 5, 6))
        ));
        List<Profile.Address> addresses = p.collectAddresses(profiles);
        assertThat(addresses.stream().allMatch(
                address -> address.getClass().getName().contains("Profile.Address")
                ),
                is(false));
    }
}
