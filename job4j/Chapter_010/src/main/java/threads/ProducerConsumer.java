package threads;

public class ProducerConsumer {

    private final Object lock = new Object();
    private boolean blockConsumer = true;

    public void doSomething() throws InterruptedException {
        synchronized (this.lock) {
            while (this.blockConsumer) {
                System.out.println(String.format("%s wait", Thread.currentThread().getId()));
                lock.wait();
            }
            System.out.println(String.format("%s useful work", Thread.currentThread().getId()));
        }
    }

    public void changeBlock(boolean enable) {
        synchronized (this.lock) {
            System.out.println(String.format("%s enable", Thread.currentThread().getId()));
            this.blockConsumer = enable;
            this.lock.notify();
        }
    }

    public static void main(String[] args) {
        final ProducerConsumer blockingWork = new ProducerConsumer();
        Thread producer = new Thread(() -> blockingWork.changeBlock(true));
        Thread consumer = new Thread(() -> {
            try {
                blockingWork.doSomething();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }

}
