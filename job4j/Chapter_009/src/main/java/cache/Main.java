package cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
    private static void print(File file) {
        String line;
        try (
                BufferedReader reader = new BufferedReader(
                        new FileReader(file)
                )
        ) {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AbstractCache<String, File> fileCache = new FileCache<>();

        print(fileCache.getFromCache("job4j/Chapter_009/src/main/resources/Names.txt").get());
        System.out.println();
        print(fileCache.getFromCache("job4j/Chapter_009/src/main/resources/Addresses.txt").get());
    }

    @SuppressWarnings("unchecked")
    private static class FileCache<K, T> extends AbstractCache<K, T> {
        public FileCache() {
            super(
                    k -> (T) new File((String) k),
                    (k, t) -> k.equals(((File) t).getName())
            );
        }
    }
}
