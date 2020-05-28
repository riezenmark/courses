package examples;

import java.util.*;

public class SetExample {

    static class User implements Comparable<User> {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(User o) {
            return this.name.compareTo(o.name);
        }
    }

    public static void main(String[] args) {
        Set<User> users = new TreeSet<>();

        users.addAll(Arrays.asList(
                new User("C"),
                new User("A"),
                new User("B")
        ));

        System.out.println(users);

        Set<Integer> set = new TreeSet<>();

        set.add(3);
        set.add(1);
        set.add(4);
        set.add(0);

        set.add(4);

        for (Integer value : set) {
            System.out.println(value);
        }
    }
}
