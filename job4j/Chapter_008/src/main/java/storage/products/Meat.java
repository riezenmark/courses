package storage.products;

import storage.Food;
import java.util.Date;

public class Meat extends Food {
    public Meat(Date productionDate, Date expirationDate, double price) {
        super("Meat", expirationDate, productionDate, price);
    }

    public void fry() {
        System.out.println("Meat has been fried.");
    }
}
