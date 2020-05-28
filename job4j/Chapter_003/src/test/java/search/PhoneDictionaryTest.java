package search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {

    @Test
    public void whenFindBySurnamePart() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Mark", "Samoylov", "6625993", "Vladivostok")
        );
        List<Person> persons = phones.find("lov");
        assertThat(persons.iterator().next().getPhone(), is("6625993"));
    }

    @Test
    public void whenNotFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Mark", "Samoylov", "6625993", "Vladivostok")
        );
        List<Person> persons = phones.find("Ivan");
        assertThat(persons.isEmpty(), is(true));
    }
}
