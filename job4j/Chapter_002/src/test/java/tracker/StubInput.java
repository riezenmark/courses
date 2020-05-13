package tracker;

public class StubInput implements Input {
    private final String[] answers;
    private int position = 0;
    private int key;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        System.out.print(question);
        String answer = answers[position];
        System.out.println(answer);
        position++;
        return answer;
    }

    @Override
    public int key() {
        return key;
    }

    @Override
    public int askInt(String question) {
        key = Integer.parseInt(ask(question));
        return key;
    }

}
