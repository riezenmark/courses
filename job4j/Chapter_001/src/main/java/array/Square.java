package array;

/**
 * Class for filling an array with the squares of given amount of integers.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class Square {
    /**
     * Creates an array filled with the squares of given amount of integers.
     * @param bound Amount of integers.
     * @return filled array array.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound;) {
            rst[i] = (int) Math.pow(++i, 2);
        }
        return rst;
    }
}
