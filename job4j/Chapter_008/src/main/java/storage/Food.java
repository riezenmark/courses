package storage;

import java.util.Date;

public class Food {
    private final String name;
    private final Date expirationDate;
    private final Date productionDate;
    private final double price;
    private double discount;

    public Food(String name, Date expirationDate, Date productionDate, double price) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.productionDate = productionDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
