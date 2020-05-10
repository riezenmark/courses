package array;

/**
 * Class for searching elements in arrays.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class FindLoop {
    /**
     * Finds an index of an element with given value if exists.
     * @param data Array to be examined.
     * @param el Value of an element.
     * @return Index of found element or -1 if an element was not found.
     */
    public static int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Finds an index of an element with given value in diapason if exists.
     * @param data Array to be examined.
     * @param el Value of an element.
     * @param start Starting index of a diapason.
     * @param finish Last index of a diapason.
     * @return Index of found element or -1 if an element was not found.
     */
    public static int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1;
        for (int index = start; index <= finish; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
