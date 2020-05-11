package sort;

import java.util.Arrays;

/**
 * Class for merging two sorted arrays.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class Merge {
    /**
     * Creates merged sorted array from two given sorted arrays.
     * @param left First array.
     * @param right Second array.
     * @return Merged array.
     */
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            rsl[k++] = left[i] < right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) {
            rsl[k++] = left[i++];
        }
        while (j < right.length) {
            rsl[k++] = right[j++];
        }
        return rsl;
    }

    public static void main(String[] args) {
        Merge process = new Merge();
        int[] rsl = process.merge(
                new int[] {1, 3, 5},
                new int[] {2, 4}
        );
        System.out.println(Arrays.toString(rsl));
    }
}

