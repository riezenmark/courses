package tracker;

import java.util.function.Consumer;

/**
 * Class for input from array of Strings.
 */
public class StubInput implements Input {
    /**
     * Array of Strings to input.
     */
    private final String[] answers;
    /**
     * Current input String array position.
     */
    private int position = 0;
    /**
     * Output buffer.
     */
    private final Consumer<String> outputBuffer;

    /**
     * Adds input Strings to array.
     * @param answers Input Strings array.
     */
    public StubInput(String[] answers, Consumer<String> outputBuffer) {
        this.answers = answers;
        this.outputBuffer = outputBuffer;
    }

    /**
     * Prints question, then prints and returns current
     * input String and goes to next one.
     * @param question Question to print.
     * @return Current input String.
     */
    @Override
    public String ask(String question) {
        outputBuffer.accept(question);
        String answer = answers[position];
        outputBuffer.accept(answer + "\n");
        position++;
        return answer;
    }

    /**
     * Prints question on the screen and returns integer user input,
     * or throws an exception if input is not in menu items.
     * @param question Question to print.
     * @param range Range.
     * @return Integer input.
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.parseInt(ask(question));
        boolean exists = false;
        for (int value : range) {
            if (value == key) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new MenuOutException("Range is " + range.length + ". Tried to access: " + key);
        }
        return key;
    }
}
