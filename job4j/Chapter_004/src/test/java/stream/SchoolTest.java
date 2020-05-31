package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import stream.School.Student;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    School school = new School();

    List<Student> students = Arrays.asList(
            new Student("B", 70),
            new Student("G", 80),
            new Student("D", 20),
            new Student("C", 10),
            new Student("E", 30),
            new Student("F", 1),
            new Student("I", 40),
            new Student("A", 90),
            new Student("H", 100),
            new Student("O", 60),
            new Student("M", 50),
            new Student("N", 69),
            new Student("L", 49),
            new Student("K", 51),
            new Student("J", 64)
    );

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

    @Test
    public void whenToMap() {
        Map<String, Student> result = school.toMap(students);
        assertThat(result.get("C").getScore(), is(10));
    }

    @Test
    public void whenLevelOf() {
        List<Student> result = school.levelOf(students, 70);
        StringBuilder resultingString = new StringBuilder();
        result.forEach(student -> resultingString.append(student.getSurname()));

        List<Student> expected = Arrays.asList(
                new Student("H", 100),
                new Student("A", 90),
                new Student("G", 80),
                new Student("B", 70)
        );
        StringBuilder expectedString = new StringBuilder();
        expected.forEach(student -> expectedString.append(student.getSurname()));

        assertThat(resultingString.toString(), is(expectedString.toString()));
    }
}
