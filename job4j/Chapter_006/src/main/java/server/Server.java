package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 5000; //any integer from 1025 to 65535
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for Server Connection.");
            Socket socket = serverSocket.accept();
            System.out.println("Connected.");

            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();

            DataInputStream in = new DataInputStream(socketInputStream);
            DataOutputStream out = new DataOutputStream(socketOutputStream);

            String s = null;
            while (true) {
                s = in.readUTF();
                System.out.println("Message Accepted: "  + s);
                System.out.println("Sending Back.");
                out.writeUTF(s);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
