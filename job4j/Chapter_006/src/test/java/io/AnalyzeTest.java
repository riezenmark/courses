package io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnalyzeTest {
    @Test
    public void test() {
        Analyze analyze = new Analyze();
        analyze.unavailable("server.log", "unavailable.csv");
        StringJoiner result = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader("unavailable.csv"))) {
            reader.lines().forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result.toString(), is("10:57:01;10:59:01;\n11:01:02;11:02:02;\n12:57:01;12:59:01;"));
    }
}