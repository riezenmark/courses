package tracker;

public interface Input {
    int key();

    String ask(String question);

    int askInt(String question);
}
