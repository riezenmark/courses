package stream;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    List<Profile.Address> collectAddresses(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }
}
