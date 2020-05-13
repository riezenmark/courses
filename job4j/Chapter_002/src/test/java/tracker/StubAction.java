package tracker;

public class StubAction implements UserAction {
    private boolean call = false;
    private final int key;

    public StubAction(int key) {
        this.key = key;
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        call = true;
        return false;
    }

    @Override
    public String info() {
        String name = "Stub Action";
        return String.format("%s. %s", key, name);
    }


    public boolean isCall() {
        return call;
    }
}
