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
        list.sort(Comparator.comparingInt(o -> o.getName().length()));
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        list.sort(Comparator.comparing(User::getName).thenComparingInt(User::getAge));
        return list;
    }
}
