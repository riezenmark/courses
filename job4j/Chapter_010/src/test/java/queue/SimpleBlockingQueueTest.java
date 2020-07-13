package queue;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {
    private final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

    @Test
    public void whenProducerAddsOneElementAndConsumerGetsElementAfterItThenElementIsGot()
            throws InterruptedException {
        Thread producer = new Thread(
                () -> {
                    try {
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

    @Test
    public void whenProducerAddsSixElementsThenProducerWaitsForConsumer()
            throws InterruptedException {
        Thread producer = new Thread(
                () -> {
                    try {
                        for (int i = 1; i <= 6; i++) {
                            queue.offer(i);
                            System.out.println(i + " added.");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Integer[] result = new Integer[6];
        Thread consumer = new Thread(
                () -> {
                    try {
                        Thread.sleep(1500);
                        result[0] = queue.poll();
                        Thread.sleep(1500);
                        for (int i = 1; i <= 5; i++) {
                            result[i] = queue.poll();
                        }
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
        assertThat(result[1], is(2));
        assertThat(result[2], is(3));
        assertThat(result[3], is(4));
        assertThat(result[4], is(5));
        assertThat(result[5], is(6));
    }

    @Test
    public void whenProducerAndConsumerWaitsForEachOther()
            throws InterruptedException {
        Thread producer = new Thread(
                () -> {
                    try {
                        Thread.sleep(1500);
                        for (int i = 1; i <= 7; i++) {
                            queue.offer(i);
                            System.out.println(i + " added.");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Integer[] result = new Integer[7];
        Thread consumer = new Thread(
                () -> {
                    try {
                        result[0] = queue.poll();
                        Thread.sleep(1500);
                        for (int i = 1; i <= 5; i++) {
                            result[i] = queue.poll();
                        }
                        result[6] = queue.poll();
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
        assertThat(result[1], is(2));
        assertThat(result[2], is(3));
        assertThat(result[3], is(4));
        assertThat(result[4], is(5));
        assertThat(result[5], is(6));
        assertThat(result[6], is(7));
    }
}