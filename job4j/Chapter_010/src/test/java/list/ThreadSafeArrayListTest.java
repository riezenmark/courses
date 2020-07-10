package list;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ThreadSafeArrayListTest {
    @Test
    public void whenEmptyConstructorThenListIsEmpty() {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();

        assertThat(list.size(), is(0));
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void whenListConstructorThenListIsEqualToOrigin() {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>(
                new SimpleArrayList<>(new Integer[] {1, 2, 3, 4, 5})
        );

        assertThat(list.size(), is(5));
    }

    @Test
    public void whenArrayConstructorThenListIsNewListWithSizeOfArray() {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>(new Integer[] {1, 2, 3, 4, 5});

        assertThat(list.size(), is(5));
    }

    @Test
    public void whenThreeTreadsAddSixElementsThenListHasSixElements() throws InterruptedException {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();
        Thread thread1 = new Thread(() -> {
            list.add(1);
            list.add(4);
        });
        Thread thread2 = new Thread(() -> {
            list.add(2);
            list.add(5);
        });
        Thread thread3 = new Thread(() -> {
            list.add(3);
            list.add(6);
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(list.size(), is(6));
    }

    @Test
    public void whenThreeTreadsAddSixElementsWithIndexesThenListHasSixElements() throws InterruptedException {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();
        Thread thread1 = new Thread(() -> {
            list.add(0, 1);
            list.add(3, 4);
        });
        Thread thread2 = new Thread(() -> {
            list.add(1, 2);
            list.add(4, 5);
        });
        Thread thread3 = new Thread(() -> {
            list.add(2, 3);
            list.add(5, 6);
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(list.size(), is(6));
    }

    @Test
    public void whenGetElementThenListReturnsElement() {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();

        list.add(8);

        assertThat(list.get(0), is(8));
    }
}