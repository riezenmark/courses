package examples;

import java.util.*;
import java.util.function.Consumer;

public class ListExample {

    static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "User{"
                    + "name='" + name + '\''
                    + '}';
        }
    }

    public static void main(String[] args) {
        List<User> users1 = new ArrayList<>();
        users1.add(new User("C"));
        users1.add(new User("A"));
        users1.add(new User("B"));
        System.out.println(users1);

        users1.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.name.compareTo(o2.name);
                    }
                }
        );

        System.out.println(users1);

        List<User> users = new ArrayList<>();
        users.add(new User("First"));
        boolean result = users.contains(new User("First"));
        System.out.println(result);
        User user = new User("Second");
        users.add(user);
        System.out.println(users.contains(user));

        users.addAll(Arrays.asList(new User("Third"), new User("Fourth")));
        System.out.println("toString");
        for (User u : users) {
            System.out.println(u);
        }

        Iterator<User> iterator = users.iterator();
        System.out.println("Iterator");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("forEach Consumer");
        users.forEach(
                new Consumer<User>() {
                    @Override
                    public void accept(User u) {
                        System.out.println(u);
                    }
                }
        );

        System.out.println("forEach lambda");
        users.forEach(u -> System.out.println(u));

        System.out.println("forEach method reference");
        users.forEach(System.out::println);

        List<Integer> list = new LinkedList<>();
        list.add(425);
        Integer value = list.get(0);
        System.out.println(value);

        list.add(1, 32);
        System.out.println(list.get(1));

        List<Integer> anotherList = new ArrayList<>();
        anotherList.add(35);
        anotherList.add(64);
        list.addAll(anotherList);
        System.out.println(list.get(2) + " " + list.get(3));

        System.out.println(String.format("Index of 35 is : %s", list.indexOf(35)));

        System.out.println(String.format("Last index of 32 is : %s", list.lastIndexOf(32)));

        list.remove(1);
        System.out.println("Element with index 1 has been removed");
        System.out.println(String.format("Index of 32 is : %s", list.indexOf(32)));
        System.out.println(String.format("Index of 35 is : %s", list.indexOf(35)));

    }
}
