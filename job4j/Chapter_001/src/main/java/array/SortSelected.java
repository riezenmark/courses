package array;

/**
 * Class for selection sort of an array.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class SortSelected {
    /**
     * Sorts array with selection method.
     * @param data An array to be sorted.
     * @return Sorted array.
     */
    public static int[] sort(int[] data) {
        for (int current = 0; current < data.length; current++) {
            int min = MinDiapason.findMin(data, current, data.length - 1);
            int index = FindLoop.indexOf(data, min, current, data.length - 1);
            int tmp = data[current];
            data[current] = data[index];
            data[index] = tmp;
        }
        return data;
    }
}
