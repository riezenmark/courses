package storage.places;

import storage.Food;
import java.util.ArrayList;

public class Shop {
    private final ArrayList<Food> storage = new ArrayList<>();
    private final String name;

    public Shop(String name) {
        this.name = name;
    }

    public void put(Food food) {
        storage.add(food);
    }

    public ArrayList<Food> getStorage() {
        return storage;
    }
}
