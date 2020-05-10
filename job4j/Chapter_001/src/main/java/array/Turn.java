package array;

/**
 * Class for turning array backwards.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class Turn {
    /**
     * Turns array backwards.
     * @param array Given array.
     * @return Turned array.
     */
    public int[] back(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int tmp = array[index];
            array[index] = array[array.length - index - 1];
            array[array.length - index - 1] = tmp;
        }
        return array;
    }
}
