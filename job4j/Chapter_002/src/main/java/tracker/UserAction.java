package tracker;

/**
 * An interface for user actions.
 */
public interface UserAction {

    /**
     * @return Key of action.
     */
    int key();

    /**
     * Executes action.
     * @param input Input.
     * @param tracker Tracker.
     * @return True if operation was successful and false if wasn't.
     */
    boolean execute(Input input, Tracker tracker);

    /**
     * @return Information about action.
     */
    String info();

}
