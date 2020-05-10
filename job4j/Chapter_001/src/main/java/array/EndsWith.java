package array;

/**
 * Class for finding out if the end of the word matches with pattern.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class EndsWith {
    /**
     * Finds out if the beginning of the word matches with pattern.
     * @param word Word.
     * @param post Pattern.
     * @return Comparison result.
     */
    public static boolean endsWith(char[] word, char[] post) {
        boolean result = true;
        for (int index = 0; index < post.length; index++) {
            if (word[word.length - post.length + index] != post[index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
