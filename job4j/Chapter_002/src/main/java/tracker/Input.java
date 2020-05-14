package tracker;

/**
 * An interface for inputs.
 */
public interface Input {

    /**
     * Prints question on the screen and returns user input String.
     * @param question Question to print.
     * @return User input String.
     */
    String ask(String question);

    /**
     * Prints question on the screen and returns integer user input from range.
     * @param question Question to print.
     * @param range Range.
     * @return Integer user input.
     */
    int ask(String question, int[] range);
}
