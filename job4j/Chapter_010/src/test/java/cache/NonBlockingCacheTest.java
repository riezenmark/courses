package cache;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class NonBlockingCacheTest {
    private final NonBlockingCache cache = new NonBlockingCache();
    AtomicReference<Exception> ex = new AtomicReference<>();

    @Before
    public void addBasesToMap() {
        cache.add(new Base(1));
    }

    @Test
    public void whenTwoThreadsAddTwoModelsWithSameIdThenCacheHasOneModel()
            throws InterruptedException {
        Thread thread = new Thread(() -> cache.add(new Base(1)));

        thread.start();
        thread.join();

        assertThat(cache.size(), is(1));
    }

    @Test
    public void whenTwoThreadsAddTwoModelsAndDeleteOneModelThenCacheHasOneModel()
            throws InterruptedException {
        Base model = new Base(2);
        Thread thread1 = new Thread(() -> cache.add(model));
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cache.delete(model);
        });


        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertThat(cache.size(), is(1));
    }

    @Test
    public void whenModelIsUpdatedThenCacheHasNewVersionOfModel() {
        Base model = new Base(1);

        cache.add(model);
        cache.update(model);

        assertThat(cache.getCache().get(model.getId()).getVersion(), is(1));
    }

    @Test
    public void whenTwoTreadsUpdateOneBaseThenThrowException() throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(ex.get().getMessage(), is("Model has already been changed."));
    }
}
