package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class UserConvert {
    public static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{"
                    + "name='" + name + '\''
                    + '}';
        }
    }

    public List<User> convert(List<String> names, Function<String, User> op) {
        List<User> users = new ArrayList<>();
        names.forEach(
                n -> users.add(op.apply(n))
        );
        return users;
    }

    public static void badMethod() throws Exception {
    }

    public interface Wrapper<T> {
        T get();
        void set(T value);
        boolean isEmpty();
    }

    public static class ExpHold<T> implements Wrapper<T> {

        private T value;

        @Override
        public T get() {
            return this.value;
        }

        @Override
        public void set(T value) {
            this.value = value;
        }

        @Override
        public boolean isEmpty() {
            return this.value == null;
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("Petr", "Nick", "Ban");
        UserConvert users = new UserConvert();
        List<User> data = users.convert(names, User::new);
        data.forEach(System.out::println);

        final StringBuilder last = new StringBuilder();

        /*String last = null;   - wrong.
        names.forEach(
                n -> last = n
        );
         */

        names.forEach(
                n ->  {
                    last.ensureCapacity(0);
                    last.append(n);
                }
        );

        Wrapper<Exception> ex = new ExpHold<>();
        names.forEach(
                n ->  {
                    try {
                        badMethod();
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        if (!ex.isEmpty()) {
            throw ex.get();
        }
    }
}
