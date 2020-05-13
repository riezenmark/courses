package tracker;

public interface Input {
    int key();

    String ask(String question);

    int ask(String question, int[] range);
}
