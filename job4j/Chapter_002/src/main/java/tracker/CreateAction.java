package tracker;

public class CreateAction implements UserAction {
    private final int key;
    private final String name;

    public CreateAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String info() {
        return String.format("%s. %s", key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        switch (input.key()) {
            case 0:
                return new AddItem(key, name).execute(input, tracker);
            case 1:
                return new ShowAllItems(key, name).execute(input, tracker);
            case 2:
                return new EditItem(key, name).execute(input, tracker);
            case 3:
                return new DeleteItem(key, name).execute(input, tracker);
            case 4:
                return new FindItemById(key, name).execute(input, tracker);
            case 5:
                return new FindItemByName(key, name).execute(input, tracker);
            case 6:
                return new ExitProgram(key, name).execute(input, tracker);
            default:
                System.out.println("Invalid key. Try again.");
                return true;
        }
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
