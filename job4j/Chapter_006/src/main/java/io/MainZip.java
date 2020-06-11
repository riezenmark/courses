package io;

import java.io.File;

public class MainZip {
    public static void main(String[] args) {
        Args arguments = new Args(args);
        Zip zip = new Zip();
        zip.pack(zip.seekBy(arguments.directory(), arguments.exclude()), new File(arguments.output()));
    }
}
