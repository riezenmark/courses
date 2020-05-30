package tracker;

import java.util.function.Consumer;

/**
 * Decorator class for input validation.
 */
public class ValidateInput implements Input {

    /**
     * Input for validation.
     */
    private final Input input;
    /**
     * Output buffer.
     */
    private final Consumer<String> outputBuffer;

    /**
     * Gets input for validation and sets it as a local field.
     * @param input Input.
     */
    public ValidateInput(final Input input, Consumer<String> outputBuffer) {
        this.input = input;
        this.outputBuffer = outputBuffer;
    }

    /**
     * Runs ask method from received input class.
     * @param question Question to print.
     * @return String that was returned from received input class.
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Prints question on the screen and returns integer user input,
     * or prints an error message if input was not a number from range
     * and asks for a new input.
     * @param question Question to print.
     * @param range Range.
     * @return Integer input.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                valid = true;
            } catch (MenuOutException moe) {
                //moe.printStackTrace();    //full exception log output
                outputBuffer.accept("This is not a Key from Menu. Please,"
                        + " input an Integer Number in range from 0 to " + (range.length - 1) + "\n");
            } catch (NumberFormatException nfe) {
                outputBuffer.accept("Invalid Input. This is not an Integer Number. Please, try Again.\n");
            }
        } while (!valid);
        return value;
    }
}
