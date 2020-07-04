package generator;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class GeneratorTest {
    Generator generator = new SimpleGenerator();

    @Test
    public void whenOneNameShouldReplaceName() {
        String template = "Hello, ${name}.";
        String[] data = new String[] {"name -> Mark"};

        String result = generator.generate(template, data);

        assertThat(result, is("Hello, Mark."));
    }

    @Test
    public void whenNameAndSubjectShouldReplaceBoth() {
        String template = "I am ${name}, who are ${subject}?";
        String[] data = new String[] {"name -> Mark", "subject -> you"};

        String result = generator.generate(template, data);

        assertThat(result, is("I am Mark, who are you?"));
    }

    @Test
    public void whenThreeShouldReplaceThree() {
        String template = "Help, ${sos}, ${sos}, ${sos}!";
        String[] data = new String[] {"sos -> aaa"};

        String result = generator.generate(template, data);

        assertThat(result, is("Help, aaa, aaa, aaa!"));
    }
}