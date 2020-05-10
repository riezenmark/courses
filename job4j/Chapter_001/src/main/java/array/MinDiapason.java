package array;

/**
 * Class for finding a minimal array element in given diapason.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class MinDiapason {
    /**
     * Finds a minimal array element in given diapason.
     * @param data Array to be examined.
     * @param start Starting index of a diapason.
     * @param finish Last index of a diapason.
     * @return Value of found element.
     */
    public static int findMin(int[] data, int start, int finish) {
        int min = data[start];
        for (int index = start + 1; index <= finish; index++) {
            if (data[index] < min) {
                min = data[index];
            }
        }
        return min;
    }
}
