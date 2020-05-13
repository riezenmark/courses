package tracker;

public class StartUI {
    private final Input input;
    private final Tracker tracker;
    private final int operations = 7;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        int[] range = new int[operations];
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range[i] = i;
        }
        do {
            menu.show();
        } while (menu.select(input.ask("Select: ", range)));
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
