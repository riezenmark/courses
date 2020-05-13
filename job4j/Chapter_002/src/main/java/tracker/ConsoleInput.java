package tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);   //System.in is input stream

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

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
