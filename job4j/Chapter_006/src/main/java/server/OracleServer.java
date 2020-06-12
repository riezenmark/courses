package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OracleServer {
    private final Socket socket;

    public OracleServer(final Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String question;
        do {
            System.out.println("Waiting for command...");
            question = in.readLine();
            System.out.println(question);
            if ("Hello.".equals(question)) {
                out.println("Hello, dear friend. I'm Oracle.");
                out.println();
            } else if ("How Old are You?".equals(question)) {
                out.println("I'am 1000 years old.");
                out.println("I was born a long time ago.");
                out.println();
            } else if (!("Exit.".equals(question))) {
                out.println("I don't understand.");
                out.println();
            }
        } while (!("Exit.".equals(question)));
        out.println("Bye!");
        out.println();
    }

    public static void main(String[] args) {
        try (final Socket socket = new ServerSocket(5001).accept()) {
            new OracleServer(socket).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
