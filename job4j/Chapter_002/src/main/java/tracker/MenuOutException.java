package tracker;

/**
 * Runtime exception that is thrown when integer user input is not in menu items.
 */
public class MenuOutException extends RuntimeException {
    /**
     * Creates message that can be seen when exception is thrown.
     * @param message Error message.
     */
    public MenuOutException(String message) {
        super(message);
    }
}
