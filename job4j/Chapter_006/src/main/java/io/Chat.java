package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chat {
    private final Scanner scanner = new Scanner(System.in);
    public void chat() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "job4j/Chapter_006/src/main/java/io/source.txt"
        ));
        PrintWriter out = new PrintWriter(new FileOutputStream(
                "job4j/Chapter_006/src/main/java/io/chat.log"
        ))) {
            reader.lines().forEach(answers::add);
            boolean active = true;
            String input;
            System.out.println("Chat Began.\n");
            do {
                input = scanner.next();
                out.println(input);
                if (input.equals("Stop.") || input.equals("Exit.")) {
                    active = false;
                }
                if (input.equals("Resume.")) {
                    active = true;
                }
                if (active) {
                    String output = answers.get((int) (Math.random() * answers.size()));
                    System.out.println(output);
                    out.println(output);
                }
            } while (!input.equals("Exit."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Chat().chat();
    }
}
