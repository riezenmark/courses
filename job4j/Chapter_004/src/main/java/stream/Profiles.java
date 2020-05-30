package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    List<Profile.Address> collectSortedUniqueAddresses(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::getAddress)
                .distinct()
                .sorted(Comparator.comparing(Profile.Address::getCity))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Profiles p = new Profiles();
        List<Profile> profiles = new ArrayList<>(Arrays.asList(
                new Profile(new Profile.Address("B", "Street", 1, 2)),
                new Profile(new Profile.Address("C", "Avenue", 3, 4)),
                new Profile(new Profile.Address("B", "Street", 1, 2)),
                new Profile(new Profile.Address("C", "Avenue", 3, 4)),
                new Profile(new Profile.Address("A", "Prospect", 5, 6))
        ));
        List<Profile.Address> addresses = p.collectSortedUniqueAddresses(profiles);

        addresses.forEach(System.out::println);
    }
}
