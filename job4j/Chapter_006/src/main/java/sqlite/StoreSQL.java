package sqlite;

import javax.xml.bind.annotation.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connection;

    public StoreSQL(Config config) {
        this.config = config;
    }

    private void createNewDatabase() {
        String url = this.config.get("url") + "magnit.db";

        try {
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generate(int size) {
        createNewDatabase();
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS account(field integer)");
            statement.executeUpdate("DELETE FROM account");
            connection.setAutoCommit(false);
            for (int i = 1; i <= size; i++) {
                statement.execute("INSERT INTO account(field) VALUES (" + i + ")");
            }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public Entries load() {
        Entries result = new Entries();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM account");
            List<Field> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Field(resultSet.getInt("field")));
            }
            result.setEntry(list);
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entries {
        @XmlElement
        private List<Field> entry;

        public Entries() {
        }

        public Entries(List<Field> entry) {
            this.entry = entry;
        }

        public List<Field> getEntry() {
            return entry;
        }

        public void setEntry(List<Field> entry) {
            this.entry = entry;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Field {
        private int field;

        public Field() {
        }

        public Field(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
