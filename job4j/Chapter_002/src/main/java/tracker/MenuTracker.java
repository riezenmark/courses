package tracker;

import java.util.ArrayList;
import java.util.function.Consumer;

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
    private final ITracker tracker;
    /**
     * Output buffer.
     */
    private final Consumer<String> outputBuffer;
    /**
     * Menu items.
     */
    private final ArrayList<UserAction> actions = new ArrayList<>();

    /**
     * Creates an instance of a class for given input and tracker.
     * @param input Input (user/test/another).
     * @param tracker Tracker.
     */
    public MenuTracker(Input input, ITracker tracker, Consumer<String> outputBuffer) {
        this.input = input;
        this.tracker = tracker;
        this.outputBuffer = outputBuffer;
    }

    /**
     * Adds actions in array of menu items.
     */
    public void fillActions() {
        this.actions.add(new AddItem(0, "Add new Item"));
        this.actions.add(new ShowAllItems(1, "Show all Items"));
        this.actions.add(new EditItem(2, "Edit Item"));
        this.actions.add(new DeleteItem(3, "Delete Item"));
        this.actions.add(new FindItemById(4, "Find Item by Id"));
        this.actions.add(new FindItemByName(5, "Find Item by Name"));
        this.actions.add(new ExitProgram(6, "Exit Program"));
    }

    /**
     * Adds new action in array of menu items.
     * @param action Action to add.
     */
    public void addAction(UserAction action) {
        this.actions.add(action);
    }

    /**
     * Get number of menu items.
     * @return Length of menu items array.
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * Outputs menu by printing "Menu." and calling an info() method
     * of every action in menu items array.
     */
    public void show() {
        outputBuffer.accept("Menu.\n");
        for (UserAction action : this.actions) {
            if (action != null) {
                outputBuffer.accept(action.info() + "\n");
            }
        }
    }

    /**
     * Executes an operation with given key and tells if execution was successful.
     * @param key Key of operation.
     * @return True if operation was successful and false if wasn't.
     */
    public boolean select(int key) {
        return this.actions.get(key).execute(this.input, this.tracker, this.outputBuffer);
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
        public boolean execute(Input input, ITracker tracker, Consumer<String> outputBuffer) {
            outputBuffer.accept("==== Create a new Item ====\n");
            String name = input.ask("Enter name of new Item: ");
            Item item = new Item(name);
            tracker.add(item);
            outputBuffer.accept("Item " + name + " added.\n");
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
        public boolean execute(Input input, ITracker tracker, Consumer<String> outputBuffer) {
            outputBuffer.accept("==== Show all Items ====\n");
            ArrayList<Item> items = tracker.getAll();
            int index = 1;
            for (Item item : items) {
                outputBuffer.accept(index++ + "\t" + item.getName() + "\t" + item.getId() + "\n");
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
        public boolean execute(Input input, ITracker tracker, Consumer<String> outputBuffer) {
            outputBuffer.accept("==== Edit Item ====\n");
            String id = input.ask("Enter id of Item You want to edit: ");
            String name = input.ask("Enter name of new Item: ");
            Item item = new Item(name);
            if (tracker.replace(id, item)) {
                outputBuffer.accept("Item was edited.\n");
            } else {
                outputBuffer.accept("Item was NOT edited.\n");
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
        public boolean execute(Input input, ITracker tracker, Consumer<String> outputBuffer) {
            outputBuffer.accept("==== Delete Item ====\n");
            String id = input.ask("Enter id of Item You want to delete: ");
            if (tracker.delete(id)) {
                outputBuffer.accept("Item was deleted.\n");
            } else {
                outputBuffer.accept("Item was NOT deleted.\n");
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
        public boolean execute(Input input, ITracker tracker, Consumer<String> outputBuffer) {
            outputBuffer.accept("==== Find Item by Id ====\n");
            String id = input.ask("Enter id: ");
            Item item = tracker.findById(id);
            if (item != null) {
                outputBuffer.accept("Found Item " + item.getName() + " with id " + id + "\n");
            } else {
                outputBuffer.accept("Item was NOT found.\n");
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
        public boolean execute(Input input, ITracker tracker, Consumer<String> outputBuffer) {
            outputBuffer.accept("==== Find Item by Name ====\n");
            String name = input.ask("Enter name: ");
            Item item = tracker.findByName(name);
            if (item != null) {
                outputBuffer.accept("Found Item " + name + " with id " + item.getId() + "\n");
            } else {
                outputBuffer.accept("Item was NOT found.\n");
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
        public boolean execute(Input input, ITracker tracker, Consumer<String> outputBuffer) {
            outputBuffer.accept("==== Exit ====\n");
            return false;
        }
    }
}
