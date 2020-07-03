package storage;

import storage.places.Shop;
import storage.places.Trash;
import storage.places.Warehouse;

public interface Control {
    void control(Food food, Warehouse warehouse, Shop shop, Trash trash);
}
