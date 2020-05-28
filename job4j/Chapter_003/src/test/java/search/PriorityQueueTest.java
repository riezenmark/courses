package search;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {

    @Test
    public void whenThereIsNoPriorityDuplicates() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("last", 5));
        queue.put(new Task("high", 2));
        queue.put(new Task("first", 1));
        queue.put(new Task("low", 4));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDescription(), is("first"));
    }

    @Test
    public void whenThereIsPriorityDuplicates1() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("five", 5));
        queue.put(new Task("two", 2));
        queue.put(new Task("three", 3));
        queue.put(new Task("five1", 5));
        queue.put(new Task("three1", 3));
        Task result = queue.take();
        assertThat(result.getDescription(), is("two"));
    }

    @Test
    public void whenThereIsPriorityDuplicates2() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("five", 5));
        queue.put(new Task("two", 2));
        queue.put(new Task("three", 3));
        queue.put(new Task("five1", 5));
        queue.put(new Task("two1", 2));
        Task result = queue.take();
        assertThat(result.getDescription(), is("two1"));
    }

    @Test
    public void whenOneTask() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("task", 100));
        Task result = queue.take();
        assertThat(result.getDescription(), is("task"));
    }

    @Test
    public void whenNoTasks() {
        PriorityQueue queue = new PriorityQueue();
        Task result = queue.take();
        Assert.assertNull(result);
    }
}
