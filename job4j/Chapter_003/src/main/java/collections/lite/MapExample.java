package collections.lite;

import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> students = new HashMap<>();
        students.put("Student A", 3);
        students.put("Student B", 4);
        students.put("Student A", 5);

        System.out.println(students.get("Student B"));

        for (String student : students.keySet()) {
            System.out.println(String.format("%s : %s", student, students.get(student)));
        }

        for (Integer mark : students.values()) {
            System.out.println(String.format("%s", mark));
        }
    }
}
