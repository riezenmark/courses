package examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(425);
        Integer value = list.get(0);
        System.out.println(value);

        list.add(1, 32);
        System.out.println(list.get(1));

        List<Integer> anotherList = new ArrayList<>();
        anotherList.add(35);
        anotherList.add(64);
        list.addAll(anotherList);
        System.out.println(list.get(2) + " " + list.get(3));

        System.out.println(String.format("Index of 35 is : %s", list.indexOf(35)));

        System.out.println(String.format("Last index of 32 is : %s", list.lastIndexOf(32)));

        list.remove(1);
        System.out.println("Element with index 1 has been removed");
        System.out.println(String.format("Index of 32 is : %s", list.indexOf(32)));
        System.out.println(String.format("Index of 35 is : %s", list.indexOf(35)));

    }
}
