package tracker;

import java.util.Scanner;

public class StartUI {
    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = Integer.parseInt(scanner.nextLine());
            if (select == 1) {
                System.out.println("==== Create a new Item ====");
                System.out.print("Enter name of new Item: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.add(item);
                System.out.println("Item " + name + " added.");
            } else if (select == 2) {
                System.out.println("==== Show all Items ====");
                Item[] items = tracker.getAll();
                int index = 1;
                for (Item item : items) {
                    System.out.println(index++ + "\t" + item.getName() + "\t" + item.getId());
                }
            } else if (select == 3) {
                System.out.println("==== Edit Item ====");
                System.out.print("Enter id of Item You want to edit: ");
                String id = scanner.nextLine();
                System.out.print("Enter name of new Item: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                if (tracker.replace(id, item)) {
                    System.out.println("Item was edited.");
                } else {
                    System.out.println("Item was NOT edited.");
                }
            } else if (select == 4) {
                System.out.println("==== Delete Item ====");
                System.out.print("Enter id of Item You want to delete: ");
                String id = scanner.nextLine();
                if (tracker.delete(id)) {
                    System.out.println("Item was deleted.");
                } else {
                    System.out.println("Item was NOT deleted.");
                }
            } else if (select == 5) {
                System.out.println("==== Find Item by Id ====");
                System.out.print("Enter id: ");
                String id = scanner.nextLine();
                Item item = tracker.findById(id);
                if (item != null) {
                    System.out.println("Found Item " + item.getName() + " with id " + id);
                } else {
                    System.out.println("Item was NOT found.");
                }
            } else if (select == 6) {
                System.out.println("==== Find Item by Name ====");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = tracker.findByName(name);
                if (item != null) {
                    System.out.println("Found Item " + name + " with id " + item.getId());
                } else {
                    System.out.println("Item was NOT found.");
                }
            } else if (select == 0) {
                System.out.println("==== Exit ====");
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("1. Add a new Item");
        System.out.println("2. Show all Items");
        System.out.println("3. Edit Item");
        System.out.println("4. Delete Item");
        System.out.println("5. Find Item by Id");
        System.out.println("6. Find Item by Name");
        System.out.println("0. Exit program");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}
