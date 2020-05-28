package sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    public List<User> sortByNameLength(List<User> list) {
        Comparator<User> comparator = new Comparator<>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        };
        list.sort(comparator);
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        Comparator<User> comparator = Comparator.comparing(User::getName).thenComparingInt(User::getAge);
        list.sort(comparator);
        return list;
    }
}