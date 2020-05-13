package tracker;

public class StartUI {

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (UserAction action : actions) {
            System.out.println(action.info());
        }
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(0, "Add a new Item"),
                new CreateAction(1, "Show all Items"),
                new CreateAction(2, "Edit Item"),
                new CreateAction(3, "Delete Item"),
                new CreateAction(4, "Find Item by Id"),
                new CreateAction(5, "Find Item by Name"),
                new CreateAction(6, "Exit program")
        };
        new StartUI().init(input, tracker, actions);
    }
}
