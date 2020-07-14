package nonblocking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class NonBlock {
    private volatile boolean blockConsumer = false;

    public void doSomething() {
        while (!this.blockConsumer) {
            System.out.println(String.format("%s useful work", Thread.currentThread().getId()));
        }
    }

    public void changeBlock(boolean enable) {
        System.out.println(String.format("%s enable", Thread.currentThread().getId()));
        this.blockConsumer = true;
    }

    /*
    public static void main(String[] args) {
        List<String> syncList = Collections.synchronizedList(new ArrayList<>()); //full blocking
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>(); //size() and isEmpty()
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>(); //are not guaranteed to be true
    }
     */
}
