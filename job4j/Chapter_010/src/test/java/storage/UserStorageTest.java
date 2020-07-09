package storage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {
    @Test
    public void whenEmptyConstructorThenNoUsers() {
        UserStorage storage = new UserStorage();

        assertThat(storage.size(), is(0));
    }

    @Test
    public void whenListConstructorThenSizeIsEqualToListSize() {
        UserStorage storage = new UserStorage(
                List.of(
                        new User(1),
                        new User(2),
                        new User(3)
                )
        );

        assertThat(storage.size(), is(3));
    }

    @Test
    public void whenArrayListConstructorThenSizeIsEqualToListSize() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1));
        users.add(new User(2));
        users.add(new User(3));
        UserStorage storage = new UserStorage(users);

        assertThat(storage.size(), is(3));
    }

    @Test
    public void whenThreeThreadsAddSixUsersThenSizeIsSix() throws InterruptedException {
        UserStorage storage = new UserStorage();
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.add(new User(1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.add(new User(4));
        });
        Thread thread2 = new Thread(() -> {
            storage.add(new User(2));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.add(new User(5));
        });
        Thread thread3 = new Thread(() -> {
            storage.add(new User(3));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.add(new User(6));
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(storage.size(), is(6));
    }

    @Test
    public void whenThreeThreadsUpdatesSixUsersThenUsersAreUpdated() throws InterruptedException {
        User user1 = new User(1);
        User user2 = new User(2);
        User user3 = new User(3);
        User user4 = new User(4);
        User user5 = new User(5);
        User user6 = new User(6);
        User user7 = new User(7);
        User user8 = new User(8);
        User user9 = new User(9);

        UserStorage storage = new UserStorage(
                List.of(
                        user1, user2, user3,
                        user4, user5, user6,
                        user7, user8, user9
                )
        );

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.update(new User(1, 100));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.update(new User(4, 400));
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.update(new User(2, 200));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.update(new User(5, 500));
        });
        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.update(new User(3, 300));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.update(new User(6, 600));
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(storage.getUser(1).getAmount(), is(100));
        assertThat(storage.getUser(user2).getAmount(), is(200));
        assertThat(user3.getAmount(), is(300));
        assertThat(storage.getUser(4).getAmount(), is(400));
        assertThat(storage.getUser(user5).getAmount(), is(500));
        assertThat(user6.getAmount(), is(600));
        assertThat(storage.getUser(7).getAmount(), is(0));
        assertThat(storage.getUser(user8).getAmount(), is(0));
        assertThat(user9.getAmount(), is(0));
    }

    @Test
    public void whenThreeThreadsDeletesThreeUsersThenOnlyThreeRemain() throws InterruptedException {
        User user1 = new User(1);
        User user2 = new User(2);
        User user3 = new User(3);
        User user4 = new User(4);
        User user5 = new User(5);
        User user6 = new User(6);

        UserStorage storage = new UserStorage(
                List.of(
                        user1, user2, user3,
                        user4, user5, user6
                )
        );

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.delete(user2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.delete(user4);
        });
        Thread thread2 = new Thread(() -> {
            storage.delete(user4);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.delete(user6);
        });
        Thread thread3 = new Thread(() -> {
            storage.delete(user6);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.delete(user2);
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(storage.getUser(1), is(user1));
        assertNull(storage.getUser(user2));
        assertThat(storage.getUser(user3), is(user3));
        assertNull(storage.getUser(4));
        assertThat(storage.getUser(new User(5)), is(user5));
        assertNull(storage.getUser(new User(6)));
    }

    @Test
    public void whenThreeTreadsTransferMoneyThenMoneyAreTransferred() throws InterruptedException {
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        User user3 = new User(3, 300);
        User user4 = new User(4, 400);
        User user5 = new User(5, 500);
        User user6 = new User(6, 600);

        UserStorage storage = new UserStorage(
                List.of(
                        user1, user2, user3,
                        user4, user5, user6
                )
        );

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.transfer(2, 3, 100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.transfer(4, 1, 200);
        });
        Thread thread2 = new Thread(() -> {
            storage.transfer(5, 6, 50);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.transfer(6, 1, 440);
        });
        Thread thread3 = new Thread(() -> {
            storage.transfer(5, 2, 10);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storage.transfer(4, 3, 1);
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(storage.getUser(1).getAmount(), is(740));
        assertThat(storage.getUser(user2).getAmount(), is(110));
        assertThat(user3.getAmount(), is(401));
        assertThat(storage.getUser(4).getAmount(), is(199));
        assertThat(storage.getUser(user5).getAmount(), is(440));
        assertThat(user6.getAmount(), is(210));
    }
}
