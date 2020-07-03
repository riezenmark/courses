package storage.products;

import storage.Food;
import java.util.Date;

public class Milk extends Food {
    public Milk(Date productionDate, Date expirationDate, double price) {
        super("Milk", expirationDate, productionDate, price);
    }

    public void drink() {
        System.out.println("Milk has been drunk.");
    }
}
