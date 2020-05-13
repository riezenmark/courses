package tracker;

import java.util.Arrays;
import java.util.Random;

public class TrackerSingleton {
    private TrackerSingleton() {
    }

    public static TrackerSingleton getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerSingleton INSTANCE = new TrackerSingleton();
    }

    private final Item[] items = new Item[100];
    private int position = 0;

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    public String generateId() {
        Random random = new Random();
        return String.valueOf(random.nextLong() + System.currentTimeMillis());
    }

    public boolean replace(String id, Item item) {
        int index = this.findIndexById(id);
        if (index != -1) {
            item.setId(items[index].getId());
            items[index] = item;
            return true;
        }
        return false;
    }

    public boolean delete(String id) {
        int index = this.findIndexById(id);
        if (index != -1) {
            items[index] = null;
            System.arraycopy(items, index + 1, items, index, position - 1 - index);
            position--;
            return true;
        }
        return false;
    }

    public Item[] getAll() {
        return Arrays.copyOf(items, this.position);
    }

    public Item findByName(String key) {
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public Item findById(String id) {
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    private int findIndexById(String id) {
        for (int index = 0; index < position; index++) {
            if (items[index] != null && items[index].getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }
}
