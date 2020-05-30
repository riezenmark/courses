package stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {

    public static class Student {
        private final String surname;
        private int score;

        public Student(String surname, int score) {
            this.surname = surname;
            if (score > 0 && score <= 100) {
                this.score = score;
            }
        }

        public int getScore() {
            return score;
        }

        public String getSurname() {
            return surname;
        }

        @Override
        public String toString() {
            return "Student{"
                    + "score=" + score
                    + '}';
        }
    }

    List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }

    Map<String, Student> toMap(List<Student> students) {
        return students
                .stream()
                .distinct()
                .collect(
                        Collectors.toMap(
                        Student::getSurname,
                        student -> student)
                );
    }
}
