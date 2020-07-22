package persistence;

import models.Role;
import models.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class DBStore implements UserStore<User> {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();
    private final Properties config;

    private void initConfig() {
        try (
                InputStream in
                        = DBStore.class.getClassLoader()
                        .getResourceAsStream("app.properties")
        ) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DBStore() {
        this.config = new Properties();
        this.initConfig();
        SOURCE.setUrl(config.getProperty("jdbc.url"));
        SOURCE.setUsername(config.getProperty("jdbc.username"));
        SOURCE.setPassword(config.getProperty("jdbc.password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        this.initTables();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    private void initTables() {
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute("DROP TABLE IF EXISTS \"user\"");
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS role (id SERIAL PRIMARY KEY,"
                            + "name CHARACTER VARYING (50),"
                            + "UNIQUE (name))"
            );
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS \"user\" (id SERIAL PRIMARY KEY,"
                            + "name CHARACTER VARYING (100),"
                            + "login CHARACTER VARYING (100),"
                            + "email CHARACTER VARYING (200),"
                            + "password CHARACTER VARYING (100),"
                            + "role_id INT REFERENCES role(id),"
                            + "created DATE,"
                            + "UNIQUE (login),"
                            + "UNIQUE (email))"
            );
            statement.executeUpdate("INSERT INTO role(name) VALUES ('admin')");
            statement.executeUpdate("INSERT INTO role(name) VALUES ('guest')");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public int size() {
        int count = 0;
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) FROM \"user\"");
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return count;
    }

    @Override
    public void add(User user) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO \"user\"(name, login, email, created, password, role_id)"
                                + " VALUES (?, ?, ?, ?, ?, ?)"
                )
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setDate(4, new Date(user.getCreateDate().getTime()));
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getRole().getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE \"user\" SET name=?, login=?, email=?, role_id=? WHERE id=?"
                )
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getRole().getId());
            statement.setInt(5, Integer.parseInt(user.getId()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM \"user\" WHERE id=?"
                )
        ) {
            statement.setInt(1, Integer.parseInt(id));
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"user\"");
            while (resultSet.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(resultSet.getDate("created").getTime());
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("role_id"),
                        calendar
                );
                list.add(user);
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    @Override
    public User findById(String id) {
        User user = null;
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM \"user\" WHERE id=?"
                )
        ) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(resultSet.getDate("created").getTime());
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("role_id"),
                        calendar
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean isCredential(String login, String password) {
        boolean isCredential = false;
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM \"user\" WHERE login=? AND password=?"
                )
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isCredential = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return isCredential;
    }

    @Override
    public int getRoleID(String login, String password) {
        int roleId = Role.GUEST.getId();
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT role_id FROM \"user\" WHERE login=? AND password=?"
                )
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                roleId = resultSet.getInt("roleId");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return roleId;
    }

    @Override
    public int getUserID(String login, String password) {
        int userId = 0;
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id FROM \"user\" WHERE login=? AND password=?"
                )
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userId;
    }
}
