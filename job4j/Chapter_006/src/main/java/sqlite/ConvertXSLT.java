package sqlite;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class ConvertXSLT {
    public void convert(File source, File destination, File scheme) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(
                    new StreamSource(scheme)
            );
            transformer.transform(
                    new StreamSource(source),
                    new StreamResult(destination)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
