package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int serverPort = 5000;
        String ip = "127.0.0.1";
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            System.out.println("Connecting to Server: " + serverPort);
            Socket socket = new Socket(inetAddress, serverPort);

            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();

            DataInputStream in = new DataInputStream(socketInputStream);
            DataOutputStream out = new DataOutputStream(socketOutputStream);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = null;
            System.out.print("Input phrase for sending: ");

            while (true) {
                s = reader.readLine();
                out.writeUTF(s);
                out.flush();
                s = in.readUTF();
                System.out.println("Server sent Answer: " + s);
                System.out.print("Input next phrase: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
