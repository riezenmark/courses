package tracker;

public class CreateAction implements UserAction {
    private String name;

    CreateAction(String name) {
        this.name = name;
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {

        if (input.key() == 0) {
            System.out.println("==== Create a new Item ====");
            String name = input.ask("Enter name of new Item: ");
            Item item = new Item(name);
            tracker.add(item);
            System.out.println("Item " + name + " added.");
        } else if (input.key() == 1) {
            System.out.println("==== Show all Items ====");
            Item[] items = tracker.getAll();
            int index = 1;
            for (Item item : items) {
                System.out.println(index++ + "\t" + item.getName() + "\t" + item.getId());
            }
        } else if (input.key() == 2) {
            System.out.println("==== Edit Item ====");
            String id = input.ask("Enter id of Item You want to edit: ");
            String name = input.ask("Enter name of new Item: ");
            Item item = new Item(name);
            if (tracker.replace(id, item)) {
                System.out.println("Item was edited.");
            } else {
                System.out.println("Item was NOT edited.");
            }
        } else if (input.key() == 3) {
            System.out.println("==== Delete Item ====");
            String id = input.ask("Enter id of Item You want to delete: ");
            if (tracker.delete(id)) {
                System.out.println("Item was deleted.");
            } else {
                System.out.println("Item was NOT deleted.");
            }
        } else if (input.key() == 4) {
            System.out.println("==== Find Item by Id ====");
            String id = input.ask("Enter id: ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("Found Item " + item.getName() + " with id " + id);
            } else {
                System.out.println("Item was NOT found.");
            }
        } else if (input.key() == 5) {
            System.out.println("==== Find Item by Name ====");
            String name = input.ask("Enter name: ");
            Item item = tracker.findByName(name);
            if (item != null) {
                System.out.println("Found Item " + name + " with id " + item.getId());
            } else {
                System.out.println("Item was NOT found.");
            }
        } else if (input.key() == 6) {
            System.out.println("==== Exit ====");
            return false;
        } else {
            System.out.println("Invalid key.");
        }
        return true;
    }

    @Override
    public String name() {
        return name;
    }

}
