package storage;

import storage.places.Shop;
import storage.places.Trash;
import storage.places.Warehouse;

public class Context {
    private final Warehouse warehouse;
    private final Shop shop;
    private final Trash trash;
    private final Control control;

    public Context(Warehouse warehouse, Shop shop, Trash trash, Control control) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
        this.control = control;
    }

    public void execute(Food food) {
        this.control.control(food, warehouse, shop, trash);
    }

    public void resort() {
        this.control.resort(warehouse, shop, trash);
    }
}
