package threads;

public class Usage {
    public static final class Counter {
        long count = 0;
        static String name = "test";

        /*
        //lock = this
        public synchronized void add(long value) { //more readable
            this.count += value;
        }
         */

        public void add(long value) {
            synchronized (this) { //faster
                this.count += value;
            }
        }

        //lock = Class - antipattern in most situations
        public static synchronized void echo() {
            System.out.println(name);
        }
    }

    public static final class CounterThread extends Thread {
        protected final Counter counter; //thread stack

        public CounterThread(final Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.add(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(); //heap
        Thread threadA = new CounterThread(counter); //heap
        Thread threadB = new CounterThread(counter); //heap

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(counter.count); //heap
    }
}
