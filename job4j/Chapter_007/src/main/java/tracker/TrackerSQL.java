package tracker;

import java.sql.*;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrackerSQL implements ITracker, AutoCloseable {

    private final Connection connection;
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    /*
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return this.connection != null;
    }
     */

    @Override
    public void add(Item item) {
        try (
                PreparedStatement statement = this.connection.prepareStatement(
                        "INSERT INTO item (id, name) VALUES (?, ?)"
                )
        ) {
            this.create();
            statement.setString(1, item.getId());
            statement.setString(2, item.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean replace(String id, Item item) {
        int result = 0;
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE item SET id = ?, name = ? WHERE id = ?"
                )
        ) {
            this.create();
            statement.setString(1, item.getId());
            statement.setString(2, item.getName());
            statement.setString(3, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result != 0;
    }

    @Override
    public boolean delete(String id) {
        int result = 0;
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM item WHERE id = ?"
                )
        ) {
            this.create();
            statement.setString(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result != 0;
    }

    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            this.create();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM item"
            );
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"));
                item.setId(resultSet.getString("id"));
                result.add(item);
            }
            resultSet.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item findByName(String key) {
        Item result = null;
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM item WHERE name = ?"
                )
        ) {
            this.create();
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new Item(resultSet.getString("name"));
                result.setId(resultSet.getString("id"));
            }
            resultSet.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM item WHERE id = ?"
                )
        ) {
            this.create();
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new Item(resultSet.getString("name"));
                result.setId(resultSet.getString("id"));
            }
            resultSet.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    void clear() {
        try (Statement statement = connection.createStatement()) {
            this.create();
            statement.executeUpdate("DELETE FROM item");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void create() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS "
                      + "item(id varchar(20) PRIMARY KEY, name varchar(20))"
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
