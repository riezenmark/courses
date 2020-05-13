package tracker;

public class MenuTracker {
    private final Input input;
    private final TrackerSingleton tracker;
    private final UserAction[] actions = new UserAction[8];
    private int position = 0;

    public MenuTracker(Input input, TrackerSingleton tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[position++] = new AddItem(0, "Add new Item");
        this.actions[position++] = new ShowAllItems(1, "Show all Items");
        this.actions[position++] = new EditItem(2, "Edit Item");
        this.actions[position++] = new DeleteItem(3, "Delete Item");
        this.actions[position++] = new FindItemById(4, "Find Item by Id");
        this.actions[position++] = new FindItemByName(5, "Find Item by Name");
        this.actions[position++] = new ExitProgram(6, "Exit Program");
    }

    public void addAction(UserAction action) {
        this.actions[position++] = action;
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

    private static class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public boolean execute(Input input, TrackerSingleton tracker) {
            System.out.println("==== Create a new Item ====");
            String name = input.ask("Enter name of new Item: ");
            Item item = new Item(name);
            tracker.add(item);
            System.out.println("Item " + name + " added.");
            return true;
        }
    }

    private static class ShowAllItems extends BaseAction {

        public ShowAllItems(int key, String name) {
            super(key, name);
        }

        @Override
        public boolean execute(Input input, TrackerSingleton tracker) {
            System.out.println("==== Show all Items ====");
            Item[] items = tracker.getAll();
            int index = 1;
            for (Item item : items) {
                System.out.println(index++ + "\t" + item.getName() + "\t" + item.getId());
            }
            return true;
        }

    }

    private static class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public boolean execute(Input input, TrackerSingleton tracker) {
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
    }

    private static class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public boolean execute(Input input, TrackerSingleton tracker) {
            System.out.println("==== Delete Item ====");
            String id = input.ask("Enter id of Item You want to delete: ");
            if (tracker.delete(id)) {
                System.out.println("Item was deleted.");
            } else {
                System.out.println("Item was NOT deleted.");
            }
            return true;
        }
    }

    private static class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public boolean execute(Input input, TrackerSingleton tracker) {
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
    }

    private static class FindItemByName extends BaseAction {

        public FindItemByName(int key, String name) {
            super(key, name);
        }

        @Override
        public boolean execute(Input input, TrackerSingleton tracker) {
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
    }

    private static class ExitProgram extends BaseAction {

        public ExitProgram(int key, String name) {
            super(key, name);
        }

        @Override
        public boolean execute(Input input, TrackerSingleton tracker) {
            System.out.println("==== Exit ====");
            return false;
        }
    }
}
