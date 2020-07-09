package storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

@ThreadSafe
public class User {
    private final int id;
    @GuardedBy("this")
    private int amount;

    public User(final int id) {
        this.id = id;
        this.amount = 0;
    }

    public User(final int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        synchronized (this) {
            return this.amount;
        }
    }

    public void setAmount(int amount) {
        synchronized (this) {
            this.amount = amount;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return this.id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
