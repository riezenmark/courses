package analyze;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnalyzeTest {
    @Test
    public void whenAll() {
        List<Analyze.User> was = List.of(
                new Analyze.User(1, "test1"),
                new Analyze.User(2, "test2"),
                new Analyze.User(3, "test3"),
                new Analyze.User(4, "test4"),
                new Analyze.User(5, "test5"),
                new Analyze.User(6, "test6"),
                new Analyze.User(7, "test7"),
                new Analyze.User(8, "test8"),
                new Analyze.User(9, "test9"),
                new Analyze.User(10, "test10"),
                new Analyze.User(11, "test11"),
                new Analyze.User(12, "test12"),
                new Analyze.User(13, "test13"),
                new Analyze.User(14, "test14"),
                new Analyze.User(15, "test15")
        );
        List<Analyze.User> became = List.of(
                new Analyze.User(1, "changed test1"),
                new Analyze.User(3, "test3"),
                new Analyze.User(20, "added"),
                new Analyze.User(4, "changed test4"),
                new Analyze.User(5, "test5"),
                new Analyze.User(7, "changed test7"),
                new Analyze.User(8, "test8"),
                new Analyze.User(21, "added"),
                new Analyze.User(9, "test9"),
                new Analyze.User(10, "changed test10"),
                new Analyze.User(11, "test11"),
                new Analyze.User(22, "added"),
                new Analyze.User(12, "test12"),
                new Analyze.User(14, "changed test14"),
                new Analyze.User(16, "added"),
                new Analyze.User(17, "added"),
                new Analyze.User(18, "added")
        );

        Analyze.Info changes = Analyze.diff(was, became);

        assertThat(changes.getAdded(), is(6));
        assertThat(changes.getDeleted(), is(4));
        assertThat(changes.getChanged(), is(5));
    }

    @Test
    public void whenNothing() {
        List<Analyze.User> list = List.of(
                new Analyze.User(1, "test1"),
                new Analyze.User(2, "test2"),
                new Analyze.User(3, "test3"),
                new Analyze.User(4, "test4"),
                new Analyze.User(5, "test5"),
                new Analyze.User(6, "test6"),
                new Analyze.User(7, "test7"),
                new Analyze.User(8, "test8"),
                new Analyze.User(9, "test9"),
                new Analyze.User(10, "test10"),
                new Analyze.User(11, "test11"),
                new Analyze.User(12, "test12"),
                new Analyze.User(13, "test13"),
                new Analyze.User(14, "test14"),
                new Analyze.User(15, "test15")
        );

        Analyze.Info changes = Analyze.diff(list, list);

        assertThat(changes.getAdded(), is(0));
        assertThat(changes.getDeleted(), is(0));
        assertThat(changes.getChanged(), is(0));
    }

    @Test
    public void whenOnlyAdded() {
        List<Analyze.User> was = List.of(
                new Analyze.User(1, "test1"),
                new Analyze.User(2, "test2"),
                new Analyze.User(3, "test3"),
                new Analyze.User(4, "test4"),
                new Analyze.User(5, "test5"),
                new Analyze.User(6, "test6"),
                new Analyze.User(7, "test7"),
                new Analyze.User(8, "test8"),
                new Analyze.User(9, "test9"),
                new Analyze.User(10, "test10"),
                new Analyze.User(11, "test11"),
                new Analyze.User(12, "test12"),
                new Analyze.User(13, "test13"),
                new Analyze.User(14, "test14"),
                new Analyze.User(15, "test15")
        );
        List<Analyze.User> became = List.of(
                new Analyze.User(1, "test1"),
                new Analyze.User(2, "test2"),
                new Analyze.User(3, "test3"),
                new Analyze.User(16, "added"),
                new Analyze.User(17, "added"),
                new Analyze.User(18, "added"),
                new Analyze.User(4, "test4"),
                new Analyze.User(5, "test5"),
                new Analyze.User(6, "test6"),
                new Analyze.User(19, "added"),
                new Analyze.User(20, "added"),
                new Analyze.User(21, "added"),
                new Analyze.User(7, "test7"),
                new Analyze.User(8, "test8"),
                new Analyze.User(9, "test9"),
                new Analyze.User(10, "test10"),
                new Analyze.User(22, "added"),
                new Analyze.User(11, "test11"),
                new Analyze.User(12, "test12"),
                new Analyze.User(23, "added"),
                new Analyze.User(13, "test13"),
                new Analyze.User(24, "added"),
                new Analyze.User(14, "test14"),
                new Analyze.User(15, "test15"),
                new Analyze.User(25, "added")
        );

        Analyze.Info changes = Analyze.diff(was, became);

        assertThat(changes.getAdded(), is(10));
        assertThat(changes.getDeleted(), is(0));
        assertThat(changes.getChanged(), is(0));
    }

    @Test
    public void whenOnlyChanged() {
        List<Analyze.User> was = List.of(
                new Analyze.User(1, "test1"),
                new Analyze.User(2, "test2"),
                new Analyze.User(3, "test3"),
                new Analyze.User(4, "test4"),
                new Analyze.User(5, "test5"),
                new Analyze.User(6, "test6"),
                new Analyze.User(7, "test7"),
                new Analyze.User(8, "test8"),
                new Analyze.User(9, "test9"),
                new Analyze.User(10, "test10"),
                new Analyze.User(11, "test11"),
                new Analyze.User(12, "test12"),
                new Analyze.User(13, "test13"),
                new Analyze.User(14, "test14"),
                new Analyze.User(15, "test15")
        );
        List<Analyze.User> became = List.of(
                new Analyze.User(1, "changed test1"),
                new Analyze.User(2, "test2"),
                new Analyze.User(3, "changed test3"),
                new Analyze.User(4, "test4"),
                new Analyze.User(5, "test5"),
                new Analyze.User(6, "changed test6"),
                new Analyze.User(7, "test7"),
                new Analyze.User(8, "test8"),
                new Analyze.User(9, "test9"),
                new Analyze.User(10, "changed test10"),
                new Analyze.User(11, "test11"),
                new Analyze.User(12, "test12"),
                new Analyze.User(13, "test13"),
                new Analyze.User(14, "changed test14"),
                new Analyze.User(15, "test15")
        );

        Analyze.Info changes = Analyze.diff(was, became);

        assertThat(changes.getAdded(), is(0));
        assertThat(changes.getDeleted(), is(0));
        assertThat(changes.getChanged(), is(5));
    }

    @Test
    public void whenOnlyDeleted() {
        List<Analyze.User> was = List.of(
                new Analyze.User(1, "test1"),
                new Analyze.User(2, "test2"),
                new Analyze.User(3, "test3"),
                new Analyze.User(4, "test4"),
                new Analyze.User(5, "test5"),
                new Analyze.User(6, "test6"),
                new Analyze.User(7, "test7"),
                new Analyze.User(8, "test8"),
                new Analyze.User(9, "test9"),
                new Analyze.User(10, "test10"),
                new Analyze.User(11, "test11"),
                new Analyze.User(12, "test12"),
                new Analyze.User(13, "test13"),
                new Analyze.User(14, "test14"),
                new Analyze.User(15, "test15")
        );
        List<Analyze.User> became = List.of(
                new Analyze.User(2, "test2"),
                new Analyze.User(4, "test4"),
                new Analyze.User(5, "test5"),
                new Analyze.User(7, "test7"),
                new Analyze.User(8, "test8"),
                new Analyze.User(9, "test9"),
                new Analyze.User(11, "test11"),
                new Analyze.User(12, "test12"),
                new Analyze.User(13, "test13"),
                new Analyze.User(15, "test15")
        );

        Analyze.Info changes = Analyze.diff(was, became);

        assertThat(changes.getAdded(), is(0));
        assertThat(changes.getDeleted(), is(5));
        assertThat(changes.getChanged(), is(0));
    }
}