package io;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SearchMain {
    String path;
    List<String> extensions;
    List<File> result;
    @Before
    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    public void before() {
        try {
            String tmpDirPath = System.getProperty("java.io.tmpdir");
            new File(tmpDirPath + "/j4j").mkdir();
            new File(tmpDirPath + "/j4j/1").mkdir();
            new File(tmpDirPath + "/j4j/1/1.txt").createNewFile();
            new File(tmpDirPath + "/j4j/2").mkdir();
            new File(tmpDirPath + "/j4j/2/2.txt").createNewFile();
            new File(tmpDirPath + "/j4j/2/2.1").mkdir();
            new File(tmpDirPath + "/j4j/2/2.1/2_1.txt").createNewFile();
            new File(tmpDirPath + "/j4j/3").mkdir();
            new File(tmpDirPath + "/j4j/3/3.txt").createNewFile();
            new File(tmpDirPath + "/j4j/3/3.1").mkdir();
            new File(tmpDirPath + "/j4j/3/3.2").mkdir();
            new File(tmpDirPath + "/j4j/3/3.1/3_1.txt").createNewFile();
            new File(tmpDirPath + "/j4j/3/3.2/3_2.txt").createNewFile();
            new File(tmpDirPath + "/j4j/4.txt").createNewFile();
            new File(tmpDirPath + "/j4j/5.txt").createNewFile();
            new File(tmpDirPath + "/j4j/6.pdf").createNewFile();
            new File(tmpDirPath + "/j4j/7.pdf").createNewFile();
            new File(tmpDirPath + "/j4j/8.fb2").createNewFile();
            new File(tmpDirPath + "/j4j/9.pdf").createNewFile();
            new File(tmpDirPath + "/j4j/10.fb2").createNewFile();
            path = tmpDirPath + "/j4j";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenTxt() {
        extensions = List.of(".txt");
        result = Search.files(path, extensions);

        assertThat(result.size(), is(8));
        assertThat(result.stream().allMatch(file -> file.getName().endsWith(".txt")), is(true));
    }

    @Test
    public void whenPdf() {
        extensions = List.of(".pdf");
        result = Search.files(path, extensions);

        assertThat(result.size(), is(3));
        assertThat(result.stream().allMatch(file -> file.getName().endsWith(".pdf")), is(true));
    }

    @Test
    public void whenFb2() {
        extensions = List.of(".fb2");
        result = Search.files(path, extensions);

        assertThat(result.size(), is(2));
        assertThat(result.stream().allMatch(file -> file.getName().endsWith(".fb2")), is(true));
    }

    @Test
    public void whenTxtAndPdf() {
        extensions = List.of(".txt", ".pdf");
        result = Search.files(path, extensions);

        assertThat(result.size(), is(11));
        assertThat(result.stream().allMatch(file -> file.getName().endsWith(".txt") || file.getName().endsWith(".pdf")), is(true));
    }

    @Test
    public void whenTxtAndFb2() {
        extensions = List.of(".txt", ".fb2");
        result = Search.files(path, extensions);

        assertThat(result.size(), is(10));
        assertThat(result.stream().allMatch(file -> file.getName().endsWith(".txt") || file.getName().endsWith(".fb2")), is(true));
    }

    @Test
    public void whenPdfAndFb2() {
        extensions = List.of(".pdf", ".fb2");
        result = Search.files(path, extensions);

        assertThat(result.size(), is(5));
        assertThat(result.stream().allMatch(file -> file.getName().endsWith(".pdf") || file.getName().endsWith(".fb2")), is(true));
    }

    @Test
    public void whenAll() {
        extensions = List.of(".txt", ".fb2", ".pdf");
        result = Search.files(path, extensions);

        assertThat(result.size(), is(13));
    }

    @Test
    public void whenNone() {
        extensions = List.of();
        result = Search.files(path, extensions);

        assertThat(result.isEmpty(), is(true));
    }
}