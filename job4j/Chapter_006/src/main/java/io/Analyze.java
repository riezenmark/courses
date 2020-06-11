package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analyze {
    public void unavailable(String source, String target) {
        try (
             PrintWriter out = new PrintWriter(new FileOutputStream(target));
             BufferedReader read = new BufferedReader(new FileReader(source))
        ) {
            String line = read.readLine();
            boolean start = false;
            while (line != null) {
                if (!start && (line.startsWith("400") || line.startsWith("500"))) {
                    out.print(line.substring(4) + ";");
                    start = true;
                }
                if (start && (line.startsWith("200") || line.startsWith("300"))) {
                    out.print(line.substring(4) + ";\n");
                    start = false;
                }
                line = read.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
