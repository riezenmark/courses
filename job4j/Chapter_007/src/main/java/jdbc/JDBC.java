package jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Properties;

public class JDBC {
    private static final Logger LOG = LogManager.getLogger(JDBC.class.getName());

    public static void main(String[] args) {
        try {
            System.out.println(Class.forName("org.postgresql.Driver"));
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }

        String url = "jdbc:postgresql://localhost:5432/test";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "2212");
        props.setProperty("ssl", "false");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

        url = "jdbc:postgresql://localhost:5432/test?user=postgres&password=2212&ssl=false";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

        url = "jdbc:postgresql://localhost:5432/test";
        String user = "postgres";
        String password = "2212";
        connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            //Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM test as t WHERE test.name IN ('b', 'c')");
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM test as t WHERE t.name IN (?, ?)"
            );
            statement.setString(1, "b");
            statement.setString(2, "c");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + "\t"
                        + resultSet.getString("name")
                );
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
