package tracker;

public class StartUI {
    private final Input input;
    private final TrackerSingleton tracker;

    public StartUI(Input input, TrackerSingleton tracker) {
        this.input = input;
        this.tracker = tracker;
    }

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

    public static void main(String[] args) {
        TrackerSingleton tracker = TrackerSingleton.TRACKER;
        new StartUI(new ValidateInput(new ConsoleInput()), tracker).init();
    }
}
