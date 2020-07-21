package persistence;

import models.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DBStore implements Store<User> {
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
        this.createTable();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    private void createTable() {
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute("DROP TABLE users");
            statement.execute(
                    "CREATE TABLE users (id SERIAL PRIMARY KEY,"
                            + "name CHARACTER VARYING (100),"
                            + "login CHARACTER VARYING (100),"
                            + "email CHARACTER VARYING (200),"
                            + "created DATE)"
            );
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public User add(User user) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO users(name, login, email, created) VALUES (?, ?, ?, ?)"
                )
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setDate(4, new Date(user.getCreateDate().getTime().getTime()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE users SET name=?, login=?, email=? WHERE id=?"
                )
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setInt(4, Integer.parseInt(user.getId()));
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public String delete(String id) {
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM users WHERE id=?"
                )
        ) {
            statement.setInt(1, Integer.parseInt(id));
            statement.executeUpdate();
            return "DELETED";
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return "NOT DELETED";
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(resultSet.getDate("created").getTime());
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
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
                        "SELECT * FROM users WHERE id=?"
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
                        calendar
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }
}
