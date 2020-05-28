package list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListTest {

    @Test
    public void when7ElementsThen9() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when6Elements3RowsThen6() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                3
        );
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when6Elements2RowsThen6() {
        ConvertList list = new ConvertList();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                2
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void convertTest() {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});
        List<Integer> result = ConvertList.convert(list);
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(result, is(expected));
    }
}
