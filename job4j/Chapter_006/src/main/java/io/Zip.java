package io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void pack(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(inputStream.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<File> seekBy(String root, String ext) {
        return Search.files(root, List.of(ext.substring(ext.indexOf("."))));
    }
}
