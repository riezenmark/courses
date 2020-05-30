package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;
import stream.School.Student;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    School school = new School();

    public List<Student> convert(List<Integer> scores, Function<Integer, Student> op) {
        List<Student> students = new ArrayList<>();
        scores.forEach(
                s -> students.add(op.apply(s))
        );
        return students;
    }

    List<Student> students = convert(Arrays.asList(
            90, 70, 80, 20, 10,
            30, 1, 40, 100, 60,
            50, 69, 49, 51, 64
            ),
            Student::new);

    @Test
    public void whenA() {
        List<Student> result = school.collect(students, student -> student.getScore() >= 70);
        assertThat(result.stream().allMatch(
                student -> student.getScore() >= 70 && student.getScore() <= 100),
                is(true));
    }

    @Test
    public void whenB() {
        List<Student> result = school.collect(students,
                student -> student.getScore() >= 50
                && student.getScore() < 70);
        assertThat(result.stream().allMatch(
                student -> student.getScore() >= 50 && student.getScore() < 70),
                is(true));
    }

    @Test
    public void whenC() {
        List<Student> result = school.collect(students, student -> student.getScore() < 50);
        assertThat(result.stream().allMatch(
                student -> student.getScore() > 0 && student.getScore() < 50),
                is(true));
    }
}
