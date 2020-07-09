package storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final ArrayList<User> users;

    public UserStorage() {
        this.users = new ArrayList<>();
    }

    public UserStorage(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean add(final User user) {
        synchronized (this) {
            return this.users.add(user);
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean update(final User user) {
        synchronized (this) {
            if (this.users.contains(user)) {
                this.users.get(this.users.indexOf(user)).setAmount(user.getAmount());
                return true;
            }
            return false;
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean delete(final User user) {
        synchronized (this) {
            return this.users.remove(user);
        }
    }

    public void transfer(final int fromID, final int toID, final int amount) {
        synchronized (this) {
            User user1 = users.stream().filter(
                    user -> user.getId() == fromID
            ).findFirst().orElse(null);
            User user2 = users.stream().filter(
                    user -> user.getId() == toID
            ).findFirst().orElse(null);
            if (user1 != null && user2 != null && user1.getAmount() >= amount) {
                user1.setAmount(user1.getAmount() - amount);
                user2.setAmount(user2.getAmount() + amount);
            }
        }
    }

    public int size() {
        synchronized (this) {
            return this.users.size();
        }
    }

    public User getUser(final int id) {
        synchronized (this) {
            return this.users.stream().filter(
                    user -> user.getId() == id
            ).findFirst().orElse(null);
        }
    }

    public User getUser(final User user) {
        synchronized (this) {
            int index = users.indexOf(user);
            return index > -1 ? this.users.get(index) : null;
        }
    }
}
