package array;

/**
 * Class for compression of arrays with empty places.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class Defragment {
    /**
     * Compresses array of Strings with null elements.
     * Puts null elements in the end of array.
     * @param array An array to be compressed.
     * @return Compressed array.
     */
    public static String[] compress(String[] array) {
        for (int index = 0; index < array.length - 1; index++) {
            if (array[index] == null) {
                int filled = index + 1;
                while (filled < array.length) {
                    if (array[filled] != null) {
                        array[index] = array[filled];
                        array[filled] = null;
                        break;
                    }
                    filled++;
                }
            }
            System.out.print(array[index] + " ");
        }
        return array;
    }

    public static void main(String[] args) {
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        String[] compressed = compress(input);
        System.out.println();
        for (String s : compressed) {
            System.out.print(s + " ");
        }
    }
}
