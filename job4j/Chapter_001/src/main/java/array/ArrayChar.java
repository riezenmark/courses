package array;

/**
 * Class for finding out if the beginning of the word matches with pattern.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class ArrayChar {
    /**
     * Finds out if the beginning of the word matches with pattern.
     * @param word Word.
     * @param pref Pattern.
     * @return Comparison result.
     */
    public static boolean startsWith(char[] word, char[] pref) {
        boolean result = true;
        for (int index = 0; index < pref.length; index++) {
            if (word[index] != pref[index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
