package storage.places;

import storage.Food;
import java.util.ArrayList;

public class Warehouse {
    private final ArrayList<Food> storage = new ArrayList<>();
    private final String name;

    public Warehouse(String name) {
        this.name = name;
    }

    public void put(Food food) {
        storage.add(food);
    }

    public ArrayList<Food> getStorage() {
        return storage;
    }
}
