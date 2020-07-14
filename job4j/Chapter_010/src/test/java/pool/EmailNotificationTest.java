package pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class EmailNotificationTest {
    private final EmailNotification emailNotification = new EmailSentEmulation();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream std = System.out;

    @Before
    public void setOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void setStdOut() {
        System.setOut(std);
    }

    @Test
    public void whenSendOneEmailThenEmailIsSent() {
        emailNotification.emailTo(
                new User(
                        "riezenmark",
                        "riezenmark@gmail.com"
                )
        );
        emailNotification.close();

        assertThat(
                out.toString(),
                is(
                        "Notification to riezenmark "
                                + "on email riezenmark@gmail.com"
                                + System.lineSeparator()
                                + "Add a new event to riezenmark."
                                + System.lineSeparator()
                )
        );
    }

    private static class EmailSentEmulation extends EmailNotification {
        @Override
        public void send(String subject, String body, String email) {
            System.out.println(subject);
            System.out.println(body);
        }
    }
}