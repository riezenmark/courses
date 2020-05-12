package tracker;

public interface UserAction {

    boolean execute(Input input, Tracker tracker);

    String name();
}
