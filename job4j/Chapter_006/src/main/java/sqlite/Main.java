package sqlite;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();
        config.init();
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.generate(10);
            StoreXML storeXML = new StoreXML(new File("job4j/Chapter_006/src/main/java/sqlite/magnit.xml"));
            storeXML.save(storeSQL.load());
            new ConvertXSLT().convert(
                    new File("job4j/Chapter_006/src/main/java/sqlite/magnit.xml"),
                    new File("job4j/Chapter_006/src/main/java/sqlite/transformed.xml"),
                    new File("job4j/Chapter_006/src/main/java/sqlite/scheme.xsl")
                    );
            storeXML.setFile(new File("job4j/Chapter_006/src/main/java/sqlite/transformed.xml"));
            System.out.println(storeXML.parse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
