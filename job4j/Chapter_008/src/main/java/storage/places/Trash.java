package storage.places;

import storage.Food;
import java.util.ArrayList;

public class Trash {
    private final ArrayList<Food> storage = new ArrayList<>();

    public void put(Food food) {
        storage.add(food);
    }

    public ArrayList<Food> getStorage() {
        return storage;
    }
}
