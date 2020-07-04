package menu;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Menu implements MenuItemSet {
    List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public List<MenuItem> getItems() {
        return this.menuItems;
    }

    public void execute(String name) {
        boolean found = false;
        Queue<MenuItem> queue = new LinkedList<>(menuItems);
        while (!queue.isEmpty()) {
            MenuItem item = queue.poll();
            if (item instanceof Executable && item.getName().equals(name)) {
                ((Executable) item).execute();
                found = true;
                break;
            }
            if (item instanceof MenuItemSet) {
                ((MenuItemSet) item).getItems().forEach(queue::offer);
            }
        }
        if (!found) {
            System.out.println("There is no executable menu items with name " + name);
        }
    }

    @Override
    public void formattedPrint(String marker, int times) {
        for (MenuItem item : menuItems) {
            if (item instanceof MenuItemSet) {
                ((MenuItemSet) item).formattedPrint(marker, times);
            } else {
                item.print(marker, times);
            }
        }
    }

    public static class Item implements MenuItem, Executable {
        private final String name;
        private final Action action;

        public Item(final String name, final Action action) {
            this.name = name;
            this.action = action;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public void print(String marker, int times) {
            for (int i = 0; i < times; i++) {
                System.out.print(marker);
            }
            System.out.println(this.name);
        }

        @Override
        public void execute() {
            action.execute();
        }
    }

    public static class ItemSet implements MenuItemSet, MenuItem {
        private final String name;
        private final List<MenuItem> menuItems;

        public ItemSet(final String name, final List<MenuItem> menuItems) {
            this.name = name;
            this.menuItems = menuItems;
        }

        @Override
        public List<MenuItem> getItems() {
            return this.menuItems;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public void print(String marker, int times) {
            System.out.println(String.valueOf(marker).repeat(Math.max(0, times)) + this.name);
        }

        @Override
        public void formattedPrint(String marker, int times) {
            print(marker, times);
            times++;
            for (MenuItem item : menuItems) {
                if (item instanceof MenuItemSet) {
                    ((MenuItemSet) item).formattedPrint(marker, times);
                } else {
                    item.print(marker, times);
                }
            }
        }
    }

    public static class ExecutableItemSet implements MenuItem, MenuItemSet, Executable {
        private final String name;
        private final Action action;
        private final List<MenuItem> menuItems;

        public ExecutableItemSet(
                final String name,
                final Action action,
                final List<MenuItem> menuItems
        ) {
            this.name = name;
            this.action = action;
            this.menuItems = menuItems;
        }

        @Override
        public void print(String marker, int times) {
            System.out.println(String.valueOf(marker).repeat(Math.max(0, times)) + this.name);
        }

        @Override
        public void formattedPrint(String marker, int times) {
            print(marker, times);
            times++;
            for (MenuItem item : menuItems) {
                if (item instanceof MenuItemSet) {
                    ((MenuItemSet) item).formattedPrint(marker, times);
                } else {
                    item.print(marker, times);
                }
            }
        }

        @Override
        public void execute() {
            this.action.execute();
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public List<MenuItem> getItems() {
            return this.menuItems;
        }
    }
}
