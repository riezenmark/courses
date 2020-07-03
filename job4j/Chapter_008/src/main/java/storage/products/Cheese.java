package storage.products;

import storage.Food;
import java.util.Date;

public class Cheese extends Food {

    public Cheese(Date productionDate, Date expirationDate, double price) {
        super("Cheese", expirationDate, productionDate, price);
    }

    public void cut() {
        System.out.println("Cheese has been cut.");
    }
}
