package io;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(
                    s -> !s.startsWith("#")
                    && s.contains("=")
            ).forEach(s -> values.put(
                    s.split("=")[0],
                    s.split("=")[1])
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String result = null;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            result =  read.lines().filter(
                    s -> !s.startsWith("#")
                    && s.contains(key)
            ).findFirst().map(
                    s -> s.substring(s.indexOf("=") + 1)
            ).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
