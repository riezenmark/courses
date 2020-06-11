package io;

import java.io.File;
import java.util.*;

public class Search {
    public static List<File> files(String parent, List<String> extensions) {
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(parent));
        LinkedList<File> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            File file = queue.poll();
            for (String ext : extensions) {
                if (file.getName().endsWith(ext)) {
                    list.add(file);
                    break;
                }
            }
            if (file.isDirectory()) {
                Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(queue::offer);
            }
        }
        return list;
    }
}
