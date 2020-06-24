package tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    TrackerSQL tracker;

    public Connection init() {
        try (
                InputStream in = TrackerSQL.class
                        .getClassLoader()
                        .getResourceAsStream(
                                "app.properties"
                        )
        ) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Before
    public void connect() throws SQLException {
       tracker = new TrackerSQL(ConnectionRollback.create(this.init()));

        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");

        item1.setId("1");
        item2.setId("2");
        item3.setId("3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
    }

    @After
    public void disconnect() {
        tracker.clear();
        tracker.close();
    }

    @Test
    public void getAllTest() {
        List<Item> list = tracker.getAll();

        assertThat(list.get(0).getName(), is("test1"));
        assertThat(list.get(1).getName(), is("test2"));
        assertThat(list.get(2).getName(), is("test3"));
    }

    @Test
    public void replaceTest() {
        Item item = new Item("replaced");
        List<Item> list;

        item.setId("replaced_id");
        tracker.replace("2", item);
        list = tracker.getAll();

        assertThat(list.stream().anyMatch(i -> i.getId().equals("replaced_id")), is(true));
    }

    @Test
    public void deleteTest() {
        List<Item> list;

        tracker.delete("3");
        list = tracker.getAll();

        assertThat(list.size(), is(2));
    }

    @Test
    public void findByNameTest() {
        Item item = tracker.findByName("test1");

        assertThat(item.getName(), is("test1"));
    }

    @Test
    public void findByIdTest() {
        Item item = tracker.findById("1");

        assertThat(item.getName(), is("test1"));
    }
}