package tracker;

public class StubAction implements UserAction {
    private boolean call = false;

    @Override
    public boolean execute(Input input, Tracker tracker) {
        call = true;
        return input.isValid();
    }

    @Override
    public String name() {
        return "Stub action";
    }

    public boolean isCall() {
        return call;
    }
}
