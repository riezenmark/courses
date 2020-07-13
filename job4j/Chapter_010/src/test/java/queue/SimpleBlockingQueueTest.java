package queue;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {
    private final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

    @Test
    public void whenProducerAddsOneElementAndConsumerGetsElementAfterItThenElementIsGot()
            throws InterruptedException {
        Thread producer = new Thread(() -> queue.offer(1));
        final Integer[] result = {null};
        Thread consumer = new Thread(
                () -> {
                    try {
                        Thread.sleep(1500);
                        result[0] = queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        assertThat(result[0], is(1));
    }

    @Test
    public void whenProducerAddsOneElementAfterConsumerAsksForItThenConsumerWaitsForElement()
            throws InterruptedException {
        Thread producer = new Thread(
                () -> {
                    try {
                        Thread.sleep(1500);
                        queue.offer(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        final Integer[] result = {null};
        Thread consumer = new Thread(
                () -> {
                    try {
                        result[0] = queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        assertThat(result[0], is(1));
    }


}