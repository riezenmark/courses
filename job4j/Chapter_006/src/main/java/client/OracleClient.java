package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class OracleClient {
    private final Socket socket;

    public OracleClient(final Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String response;
        String request;
        do {
            request = console.nextLine();
            out.println(request);
            response = in.readLine();
            while (!response.isEmpty()) {
                System.out.println(response);
                response = in.readLine();
            }
        } while (!("Exit.".equals(request)));
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5001);
        new OracleClient(socket).start();
    }
}
