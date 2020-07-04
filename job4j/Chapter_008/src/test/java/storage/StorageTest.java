package storage;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.core.Is.is;

import storage.places.Shop;
import storage.places.Trash;
import storage.places.Warehouse;
import storage.products.Cheese;
import storage.products.Meat;
import storage.products.Milk;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Ignore
public class StorageTest {
    private Context context;
    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;

    @Before
    public void init() {
        warehouse = new Warehouse("Warehouse-1");
        shop = new Shop("Seven-Eleven");
        trash = new Trash();
        context = new Context(
                warehouse,
                shop,
                trash,
                new QualityControl()
        );
    }

    @Test
    public void shopTest() {
        context.execute(
                new Milk(
                        new GregorianCalendar(
                                2020, Calendar.JULY, 1
                        ).getTime(),
                        new GregorianCalendar(
                                2020, Calendar.JULY, 7
                        ).getTime(),
                        1.15
                )
        );
        assertThat(shop.getStorage().get(0).getName(), is("Milk"));
        assertEquals(shop.getStorage().get(0).getDiscount(), 0, 0.01);
    }

    @Test
    public void trashTest() {
        context.execute(
                new Cheese(
                        new GregorianCalendar(
                                2020, Calendar.FEBRUARY, 1
                        ).getTime(),
                        new GregorianCalendar(
                                2020, Calendar.APRIL, 1
                        ).getTime(),
                        2.25
                )
        );
        assertThat(trash.getStorage().get(0).getName(), is("Cheese"));
    }

    @Test
    public void warehouseTest() {
        context.execute(
                new Meat(
                        new GregorianCalendar(
                                2020, Calendar.JULY, 1
                        ).getTime(),
                        new GregorianCalendar(
                                2021, Calendar.JULY, 7
                        ).getTime(),
                        2.15
                )
        );
        assertThat(warehouse.getStorage().get(0).getName(), is("Meat"));
    }

    @Test
    public void shopAndDiscountTest() {
        context.execute(
                new Meat(
                        new GregorianCalendar(
                                2020, Calendar.FEBRUARY, 2
                        ).getTime(),
                        new GregorianCalendar(
                                2020, Calendar.JULY, 6
                        ).getTime(),
                        3.36
                )
        );
        assertThat(shop.getStorage().get(0).getName(), is("Meat"));
        assertEquals(shop.getStorage().get(0).getDiscount(), 25, 0.01);
    }

    @Test
    public void resortTest() {
        Food meat = new Meat(
                new GregorianCalendar(
                        2020, Calendar.FEBRUARY, 2
                ).getTime(),
                new GregorianCalendar(
                        2020, Calendar.JULY, 6
                ).getTime(),
                3.36
        );

        context.execute(meat);
        assertThat(shop.getStorage().get(0).getName(), is("Meat"));
        assertEquals(shop.getStorage().get(0).getDiscount(), 25, 0.01);

        meat.setExpirationDate(
                new GregorianCalendar(
                        2020, Calendar.JULY, 3
                ).getTime()
        );

        context.resort();
        assertThat(shop.getStorage().isEmpty(), is(true));
        assertThat(trash.getStorage().get(0).getName(), is("Meat"));
    }
}
