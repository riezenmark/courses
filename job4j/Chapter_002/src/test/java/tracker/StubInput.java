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
    public int ask(String question, int[] range) {
        try {
            key = Integer.parseInt(ask(question));
            boolean exists = false;
            for (int value : range) {
                if (value == key) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                return key;
            } else {
                throw new MenuOutException("Range is " + range.length + ". Tried to access: " + key);
            }
        } catch (MenuOutException moe) {
            System.out.println("Out of Menu Range.");
            return 6;
        } catch (NumberFormatException nfe) {
            System.out.println("Not an Integer Number.");
            return 6;
        }
    }

}
