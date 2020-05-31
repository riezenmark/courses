package stream;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MatrixTest {
    Matrix m = new Matrix();

    @Test
    public void when3to4then12() {
        Integer[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        List<Integer> result = m.convert(matrix);
        StringBuilder s = new StringBuilder();
        result.forEach(s::append);
        assertThat(s.toString(), is("123456789101112"));
    }
}
