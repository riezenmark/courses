package io;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConfigMain {

    @Test
    public void toStringTest() {
        Config config = new Config("../app.properties");
        assertThat(
                config.toString(),
                is("## PostgreSQL\n"
                        + "\n"
                        + "hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect\n"
                        + "hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio\n"
                        + "hibernate.connection.driver_class=org.postgresql.Driver\n"
                        + "hibernate.connection.username=postgres\n"
                        + "hibernate.connection.password=password")
        );
    }

    @Test
    public void loadTest() {
        Config config = new Config("../app.properties");

        config.load();

        assertThat(config.getValues().size(), is(5));
        assertThat(config.getValues().get("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.getValues().get("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.getValues().get("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.getValues().get("hibernate.connection.username"), is("postgres"));
        assertThat(config.getValues().get("hibernate.connection.password"), is("password"));
        assertNull(config.getValues().get("## PostgreSQL"));
    }

    @Test
    public void valueTest() {
        Config config = new Config("../app.properties");

        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertNull(config.value("## PostgreSQL"));
    }

}