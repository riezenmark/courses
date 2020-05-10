package array;

/**
 * Class for creating multiplication table.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class Matrix {
    /**
     * Creates multiplication table of given size.
     * @param size Table size.
     * @return 2D array of multiplied numbers.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
