package logic;

import models.User;
import persistence.MemoryStore;
import persistence.Store;

import java.util.List;

public enum ValidateService {
    INSTANCE;
    private volatile int count = 0;

    private final Store persistence = MemoryStore.INSTANCE;

    public void add(User user) {
        if (persistence.findById(user.getId()) == null) {
            persistence.add(user);
            synchronized (this) {
                count++;
            }
        }
    }

    public void update(User user) {
        if (persistence.findById(user.getId()) != null) {
            persistence.update(user);
        }
    }

    public void delete(User user) {
        if (persistence.findById(user.getId()) != null) {
            persistence.delete(user);
        }
    }

    public int count() {
        return this.count;
    }

    public List<User> getAll() {
        return persistence.getAll();
    }

    public User findById(int id) {
        return persistence.findById(id);
    }
}
