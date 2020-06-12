package server;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OracleServerTest {
    private static final String LN = System.getProperty("line.separator");

    @Test
    public void exitTest() throws IOException {
        this.testServer("Exit.", "Bye!" + LN + LN);
    }

    @Test
    public void helloTest() throws IOException {
        this.testServer(
                String.format("Hello.%sExit.", LN),
                String.format(
                        "Hello, dear friend. I'm Oracle.%s%sBye!%s%s",
                        LN, LN, LN, LN
                )
        );
    }

    @Test
    public void randomQuestionTest() throws IOException {
        this.testServer(
                String.format("Some Question%sExit.", LN),
                String.format(
                        "I don't understand.%s%sBye!%s%s",
                        LN, LN, LN, LN
                )
        );
    }

    @Test
    public void howOldTest() throws IOException {
        this.testServer(
                String.format("How Old are You?%sExit.", LN),
                String.format(
                        "I'am 1000 years old.%s"
                        + "I was born a long time ago."
                        + "%s%sBye!%s%s",
                        LN, LN, LN, LN, LN
                )
        );
    }

    private void testServer(final String input, final String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        OracleServer server = new OracleServer(socket);

        server.start();

        assertThat(out.toString(), is(expected));
    }
}