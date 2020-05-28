package tracker;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for a singleton request tracker.
 */
public class Tracker {
    /**
     * Doesn't allow to make new instances.
     */
    private Tracker() {
    }

    /**
     * Get instance.
     * @return instance.
     */
    public static Tracker getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Holder class.
     */
    private static final class Holder {
        private static final Tracker INSTANCE = new Tracker();
    }

    private final ArrayList<Item> items = new ArrayList<>();

    public void add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
    }

    /**
     * Generate unique id for a request.
     * @return Generated id.
     */
    public String generateId() {
        Random random = new Random();
        return String.valueOf(random.nextLong() + System.currentTimeMillis());
    }

    /**
     * Replaces request of given id with a new one.
     * @param id Id.
     * @param item New request.
     * @return True if request was replaced and false if wasn't.
     */
    public boolean replace(String id, Item item) {
        int index = this.findIndexById(id);
        if (index != -1) {
            item.setId(items.get(index).getId());
            items.add(index, item);
            return true;
        }
        return false;
    }

    /**
     * Deletes request with given id.
     * @param id Id.
     * @return True if request was deleted anr false if wasn't.
     */
    public boolean delete(String id) {
        int index = this.findIndexById(id);
        if (index != -1) {
            items.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Gets all requests.
     * @return Copy of array of requests.
     */
    public ArrayList<Item> getAll() {
        return new ArrayList<>(this.items);
    }

    /**
     * Finds request with given name.
     * @param key Name for search.
     * @return Found request or null.
     */
    public Item findByName(String key) {
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Finds request with given id.
     * @param id Id for search.
     * @return Found request or null.
     */
    public Item findById(String id) {
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Founds request with given id and returns its index in array.
     * @param id Id for search.
     * @return Index of found request or -1.
     */
    private int findIndexById(String id) {
        for (int index = 0; index < this.items.size(); index++) {
            if (items.get(index) != null && items.get(index).getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }
}
