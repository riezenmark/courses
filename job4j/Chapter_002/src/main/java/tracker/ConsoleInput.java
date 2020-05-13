package tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private int key;
    private Scanner scanner = new Scanner(System.in);   //System.in is input stream

    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        key = Integer.parseInt(ask(question));
        return key;
    }

    @Override
    public int key() {
        return key;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
