package tracker;

public class MenuTracker {
    private final Input input;
    private final Tracker tracker;
    private final UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = new AddItem(0, "Add new Item");
        this.actions[1] = new ShowAllItems(1, "Show all Items");
        this.actions[2] = new EditItem(2, "Edit Item");
        this.actions[3] = new DeleteItem(3, "Delete Item");
        this.actions[4] = new FindItemById(4, "Find Item by Id");
        this.actions[5] = new FindItemByName(5, "Find Item by Name");
        this.actions[6] = new ExitProgram(6, "Exit program");
    }

    public int getActionsLength() {
        return this.actions.length;
    }

    public void show() {
        System.out.println("Menu.");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    public boolean select(int key) {
        return this.actions[key].execute(this.input, this.tracker);
    }

    private static class AddItem implements UserAction {

        private final int key;
        private final String name;

        public AddItem(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public boolean execute(Input input, Tracker tracker) {
            System.out.println("==== Create a new Item ====");
            String name = input.ask("Enter name of new Item: ");
            Item item = new Item(name);
            tracker.add(item);
            System.out.println("Item " + name + " added.");
            return true;
        }

        @Override
        public String info() {
            return String.format("%s. %s", key, name);
        }
    }

    private static class ShowAllItems implements UserAction {

        private final int key;
        private final String name;

        public ShowAllItems(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public boolean execute(Input input, Tracker tracker) {
            System.out.println("==== Show all Items ====");
            Item[] items = tracker.getAll();
            int index = 1;
            for (Item item : items) {
                System.out.println(index++ + "\t" + item.getName() + "\t" + item.getId());
            }
            return true;
        }

        @Override
        public String info() {
            return String.format("%s. %s", key, name);
        }

    }

    private static class EditItem implements UserAction {

        private final int key;
        private final String name;

        public EditItem(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public boolean execute(Input input, Tracker tracker) {
            System.out.println("==== Edit Item ====");
            String id = input.ask("Enter id of Item You want to edit: ");
            String name = input.ask("Enter name of new Item: ");
            Item item = new Item(name);
            if (tracker.replace(id, item)) {
                System.out.println("Item was edited.");
            } else {
                System.out.println("Item was NOT edited.");
            }
            return true;
        }

        @Override
        public String info() {
            return String.format("%s. %s", key, name);
        }

    }

    private static class DeleteItem implements UserAction {

        private final int key;
        private final String name;

        public DeleteItem(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public boolean execute(Input input, Tracker tracker) {
            System.out.println("==== Delete Item ====");
            String id = input.ask("Enter id of Item You want to delete: ");
            if (tracker.delete(id)) {
                System.out.println("Item was deleted.");
            } else {
                System.out.println("Item was NOT deleted.");
            }
            return true;
        }

        @Override
        public String info() {
            return String.format("%s. %s", key, name);
        }

    }

    private static class FindItemById implements UserAction {

        private final int key;
        private final String name;

        public FindItemById(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public boolean execute(Input input, Tracker tracker) {
            System.out.println("==== Find Item by Id ====");
            String id = input.ask("Enter id: ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("Found Item " + item.getName() + " with id " + id);
            } else {
                System.out.println("Item was NOT found.");
            }
            return true;
        }

        @Override
        public String info() {
            return String.format("%s. %s", key, name);
        }

    }

    private static class FindItemByName implements UserAction {

        private final int key;
        private final String name;

        public FindItemByName(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public boolean execute(Input input, Tracker tracker) {
            System.out.println("==== Find Item by Name ====");
            String name = input.ask("Enter name: ");
            Item item = tracker.findByName(name);
            if (item != null) {
                System.out.println("Found Item " + name + " with id " + item.getId());
            } else {
                System.out.println("Item was NOT found.");
            }
            return true;
        }

        @Override
        public String info() {
            return String.format("%s. %s", key, name);
        }

    }

    private static class ExitProgram implements UserAction {

        private final int key;
        private final String name;

        public ExitProgram(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public boolean execute(Input input, Tracker tracker) {
            System.out.println("==== Exit ====");
            return false;
        }

        @Override
        public String info() {
            return String.format("%s. %s", key, name);
        }

    }
}
