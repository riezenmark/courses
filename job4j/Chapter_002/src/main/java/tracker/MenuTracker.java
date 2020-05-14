package tracker;

/**
 * Class for work with menu.
 */
public class MenuTracker {
    /**
     * An input.
     */
    private final Input input;
    /**
     * Tracker.
     */
    private final Tracker tracker;
    /**
     * Menu items.
     */
    private final UserAction[] actions = new UserAction[8];
    /**
     * Current number of menu items.
     */
    private int position = 0;

    /**
     * Creates an instance of a class for given input and tracker.
     * @param input Input (user/test/another).
     * @param tracker Tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Adds actions in array of menu items.
     */
    public void fillActions() {
        this.actions[position++] = new AddItem(0, "Add new Item");
        this.actions[position++] = new ShowAllItems(1, "Show all Items");
        this.actions[position++] = new EditItem(2, "Edit Item");
        this.actions[position++] = new DeleteItem(3, "Delete Item");
        this.actions[position++] = new FindItemById(4, "Find Item by Id");
        this.actions[position++] = new FindItemByName(5, "Find Item by Name");
        this.actions[position++] = new ExitProgram(6, "Exit Program");
    }

    /**
     * Adds new action in array of menu items.
     * @param action Action to add.
     */
    public void addAction(UserAction action) {
        this.actions[position++] = action;
    }

    /**
     * Get number of menu items.
     * @return Length of menu items array.
     */
    public int getActionsLength() {
        return this.actions.length;
    }

    /**
     * Outputs menu by printing "Menu." and calling an info() method
     * of every action in menu items array.
     */
    public void show() {
        System.out.println("Menu.");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Executes an operation with given key and tells if execution was successful.
     * @param key Key of operation.
     * @return True if operation was successful and false if wasn't.
     */
    public boolean select(int key) {
        return this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Action for adding new requests to the tracker.
     */
    private static class AddItem extends BaseAction {

        /**
         * Creates an instance with given key and name.
         * @param key Key of action.
         * @param name Name of action.
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Creates request with given name and adds it to the tracker.
         * @param input Input.
         * @param tracker Tracker.
         * @return True if operation was successful and false if wasn't.
         */
        @Override
        public boolean execute(Input input, Tracker tracker) {
            System.out.println("==== Create a new Item ====");
            String name = input.ask("Enter name of new Item: ");
            Item item = new Item(name);
            tracker.add(item);
            System.out.println("Item " + name + " added.");
            return true;
        }
    }

    /**
     * Action for showing all requests from tracker.
     */
    private static class ShowAllItems extends BaseAction {
        
        /**
         * Creates an instance with given key and name.
         * @param key Key of action.
         * @param name Name of action.
         */
        public ShowAllItems(int key, String name) {
            super(key, name);
        }

        /**
         * Shows all items from tracker.
         * @param input Input.
         * @param tracker Tracker.
         * @return True if operation was successful and false if wasn't.
         */
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

    }

    /**
     * Action for editing request.
     */
    private static class EditItem extends BaseAction {
        
        /**
         * Creates an instance with given key and name.
         * @param key Key of action.
         * @param name Name of action.
         */
        public EditItem(int key, String name) {
            super(key, name);
        }

        /**
         * Renames request with given id with new and tells if it was renamed or not.
         * @param input Input.
         * @param tracker Tracker.
         * @return True if operation was successful and false if wasn't.
         */
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
    }

    /**
     * Action for deleting request.
     */
    private static class DeleteItem extends BaseAction {

        /**
         * Creates an instance with given key and name.
         * @param key Key of action.
         * @param name Name of action.
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Deletes request with given id and and tells if it was renamed or not.
         * @param input Input.
         * @param tracker Tracker.
         * @return True if operation was successful and false if wasn't.
         */
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
    }

    /**
     * Action for finding request by id.
     */
    private static class FindItemById extends BaseAction {

        /**
         * Creates an instance with given key and name.
         * @param key Key of action.
         * @param name Name of action.
         */
        public FindItemById(int key, String name) {
            super(key, name);
        }

        /**
         * Finds request with given id and prints its name or
         * a message that request was not found.
         * @param input Input.
         * @param tracker Tracker.
         * @return True if operation was successful and false if wasn't.
         */
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
    }

    /**
     * Action for finding request by id.
     */
    private static class FindItemByName extends BaseAction {

        /**
         * Creates an instance with given key and name.
         * @param key Key of action.
         * @param name Name of action.
         */
        public FindItemByName(int key, String name) {
            super(key, name);
        }

        /**
         * Finds request with given name and prints its id or
         * a message that request was not found.
         * @param input Input.
         * @param tracker Tracker.
         * @return True if operation was successful and false if wasn't.
         */
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
    }

    /**
     * Action for exit.
     */
    private static class ExitProgram extends BaseAction {

        /**
         * Creates an instance with given key and name.
         * @param key Key of action.
         * @param name Name of action.
         */
        public ExitProgram(int key, String name) {
            super(key, name);
        }

        /**
         * Exits program.
         * @param input Input.
         * @param tracker Tracker.
         * @return false.
         */
        @Override
        public boolean execute(Input input, Tracker tracker) {
            System.out.println("==== Exit ====");
            return false;
        }
    }
}
