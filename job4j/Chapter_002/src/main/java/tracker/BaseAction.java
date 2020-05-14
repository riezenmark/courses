package tracker;

/**
 * An abstract class for key() and info() methods.
 */
public abstract class BaseAction implements UserAction {
    /**
     * Key of action.
     */
    private final int key;
    /**
     * Name of action.
     */
    private final String name;

    /**
     * Constructor for creating an instance of a class from extending classes
     * @param key Key of action.
     * @param name Name of action.
     */
    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * @return Key of action.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * @return String in format "key. name"
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), name);
    }
}
