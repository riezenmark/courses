package tracker;

import java.util.Random;

/**
 * Class for storing requests.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class Tracker {
    /**
     * Array for storing requests.
     */
    private final Item[] items = new Item[100];

    /**
     * Pointer on array cel for new request.
     */
    private int position = 0;

    /**
     * adds new request to the storage.
     * @param item New request.
     * @return Formatted request.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Generates new unique key.
     * @return Unique key.
     */
    public String generateId() {
        /*
        Random                      class for working with random values
         */
        Random random = new Random();
        /*
        String.valueOf()            converts value to the string object.
        random.nextLong()           returns random long.
        System.currentTimeMillis()  returns long containing time since 01.01.1970 in milliseconds.
         */
        return String.valueOf(random.nextLong() + System.currentTimeMillis());
    }

    /**
     * Replaces request of given id by new one.
     * @param id Request id.
     * @param item New request.
     * @return True if request was replaced and false if wasn't.
     */
    public boolean replace(String id, Item item) {
        int index = this.findIndexById(id);
        if (index != -1) {
            items[index] = item;
            return true;
        }
        return false;
    }

    /**
     * Deletes request with given id.
     * @param id Request id.
     * @return True if request was deleted and false if wasn't.
     */
    public boolean delete(String id) {
        int index = this.findIndexById(id);
        if (index != -1) {
            items[index] = null;
            /*
            Moves all elements right from deleted element one position left.
             */
            System.arraycopy(items, index + 1, items, index, position - 1 - index);
            position--;
            return true;
        }
        return false;
    }

    /**
     * Gets all requests that are not null.
     * @return Copy of storage without null elements.
     */
    public Item[] getAll() {
        Item[] result = new Item[position];
        int newIndex = 0;
        for (int index = 0; index < position; index++) {
            if (items[index] != null) {
                result[newIndex++] = items[index];
            }
        }
        return result;
    }

    /**
     * Finds request by given name.
     * @param key Request name.
     * @return Found request or null if request was not found.
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
     * Finds request by given id.
     * @param id Request id.
     * @return Found request or null if request was not found.
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
     * Finds index of request in array by given id.
     * @param id Request id.
     * @return Found request index or -1 if request was not found.
     */
    private int findIndexById(String id) {
        for (int index = 0; index < position; index++) {
            if (items[index] != null && items[index].getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }
}
