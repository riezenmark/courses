package tracker;

public interface Input {
    int key();

    boolean isValid();

    String ask(String question);

    int askInt(String question);
}