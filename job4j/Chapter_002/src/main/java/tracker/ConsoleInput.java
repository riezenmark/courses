package tracker;

import java.util.Scanner;

/**
 * Class for work with user inputs from console.
 */
public class ConsoleInput implements Input {
    /**
     * Scanner object.
     */
    private final Scanner scanner = new Scanner(System.in);   //System.in is input stream

    /**
     * Prints question on the screen and returns user input String.
     * @param question Question to print.
     * @return User input String.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Prints question on the screen and returns integer user input,
     * or throws an exception if input is not in menu items.
     * @param question Question to print.
     * @param range Range of menu items.
     * @return Integer user input.
     */
    @Override
    public int ask(String question, int[] range) { //if extends Exception then "trows *exception names* {
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
