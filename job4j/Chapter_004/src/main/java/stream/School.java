package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {

    public static class Student {
        private int score;

        public Student(int score) {
            if (score > 0 && score <= 100) {
                this.score = score;
            }
        }

        public int getScore() {
            return score;
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
}
