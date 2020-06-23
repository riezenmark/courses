package sqlite;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class StoreXML {
    private File target;

    StoreXML(File target) {
        this.target = target;
    }

    public void setFile(File target) {
        this.target = target;
    }

    public void save(StoreSQL.Entries entry) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            JAXBContext jaxbContext = JAXBContext.newInstance(StoreSQL.Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entry, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long parse() {
        long sum = 0;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Entries entries = (Entries) jaxbUnmarshaller.unmarshal(target);
            List<Entry> list = entries.getEntry();
            sum = list.stream()
                    .map(entry -> (long) entry.getField())
                    .reduce((Long::sum))
                    .orElse(0L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement
    private static class Entries {
        @XmlElement
        private List<Entry> entry;

        public Entries() {
        }

        public Entries(List<Entry> entry) {
            this.entry = entry;
        }

        public List<Entry> getEntry() {
            return entry;
        }

        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Entry {
        @XmlAttribute
        private int field;

        public Entry() {
        }

        public Entry(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }
}
