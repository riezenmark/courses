package stream;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUsage {
    public static class Task {
        private final String name;
        private final long spent;

        public Task(String name, long spent) {
            this.name = name;
            this.spent = spent;
        }

        @Override
        public String toString() {
            return "Task{"
                    + "name='" + name + '\''
                    + ", spent=" + spent
                    + '}';
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = List.of(
                new Task("Bug #1", 100),
                new Task("Task #2", 100),
                new Task("Bug #3", 100)
        );

        List<Task> bugs = tasks.stream().filter(
                task -> task.name.contains("Bug")
        ).collect(Collectors.toList());
        bugs.forEach(System.out::println);

        List<String> names = tasks.stream().map(
                task -> task.name
        ).collect(Collectors.toList());
        names.forEach(System.out::println);

        long spentSum = tasks.stream().map(
                task -> task.spent
        ).reduce(0L, Long::sum);
        System.out.println(spentSum);

        List.of(2, 4, 3, 4).stream()
                .takeWhile(v -> v % 2 == 0)
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print);

        System.out.println();

        List.of(2, 4, 3, 4).stream()
                .dropWhile(v -> v % 2 == 0)
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print);

        System.out.println();

        Stream.of(1, null, 2, null, 3)
                .flatMap(Stream::ofNullable)
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print);

        System.out.println();

        Stream.iterate(0, i -> i < 10, i -> i + 1)
                .map(v -> String.format(" %s", v))
                .forEach(System.out::print);

        System.out.println();

        List.of(5, 1, 2).forEach(System.out::print);

        System.out.println();

        Set.of(5, 1, 2).forEach(System.out::print);

        System.out.println();

        Map.of("first", 1, "second", 2)
                .forEach((v, k) -> System.out.println(String.format("%s %s", v, k)));
    }
}
