package array;

/**
 * Class for checking if all the members of array are equal.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class Check {
    /**
     * Checks if all the members of array are equal.
     * @param data Examined array.
     * @return Examination result.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int index = 1; index < data.length; index++) {
            if (data[0] != data[index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
