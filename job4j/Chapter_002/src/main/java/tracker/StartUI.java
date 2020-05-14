package tracker;

/**
 * Class for starting User Interface.
 */
public class StartUI {
    /**
     * Input.
     */
    private final Input input;
    /**
     * Tracker.
     */
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Creates MenuTracker object, fills it with actions,
     * creates range of menu items and also fills it.
     * Shows menu items and asks for input while user doesn't
     * choose exit menu item.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        int operations = 8;
        int[] range = new int[operations];
        menu.fillActions();
        /*
        UserAction someNewAction = UserAction() {   //anonymous class
        }
        menu.addAction(someNewAction);
         */
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range[i] = i;
        }
        do {
            menu.show();
        } while (menu.select(input.ask("Select: ", range)));
    }

    /**
     * Starts UI for a tracker.
     * @param args args.
     */
    public static void main(String[] args) {
        Tracker tracker = Tracker.getInstance();
        new StartUI(new ValidateInput(new ConsoleInput()), tracker).init();
    }
}
