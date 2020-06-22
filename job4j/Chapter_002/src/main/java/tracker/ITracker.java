package tracker;

import java.util.ArrayList;

public interface ITracker {
    void add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    ArrayList<Item> getAll();
    Item findByName(String key);
    Item findById(String id);
}
