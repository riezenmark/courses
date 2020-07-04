package storage;

import storage.places.Shop;
import storage.places.Trash;
import storage.places.Warehouse;

import java.util.ArrayList;

public class QualityControl implements Control {

    @Override
    public void control(Food food, Warehouse warehouse, Shop shop, Trash trash) {
        double allTime = food.getExpirationDate().getTime() - food.getProductionDate().getTime();
        double passedTime = System.currentTimeMillis() - food.getProductionDate().getTime();
        double percent = passedTime / allTime * 100;
        if (percent < 25) {
            warehouse.put(food);
        } else if (percent <= 75) {
            shop.put(food);
        } else if (percent < 100) {
            food.setDiscount(25);
            shop.put(food);
        } else {
            trash.put(food);
        }
    }

    @Override
    public void resort(Warehouse warehouse, Shop shop, Trash trash) {
        ArrayList<Food> list = new ArrayList<>();
        list.addAll(warehouse.getStorage());
        list.addAll(shop.getStorage());
        list.addAll(trash.getStorage());
        warehouse.clear();
        shop.clear();
        trash.clear();
        for (Food food : list) {
            control(food, warehouse, shop, trash);
        }
    }
}
