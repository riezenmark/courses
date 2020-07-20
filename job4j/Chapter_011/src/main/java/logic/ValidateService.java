package logic;

import models.User;
import persistence.MemoryStore;
import persistence.Store;

import java.util.List;

public enum ValidateService {
    INSTANCE;

    private final Store persistence = MemoryStore.INSTANCE;

    public boolean add(User user) {
        if (persistence.findById(user.getId()) == null) {
            persistence.add(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean update(User user) {
        if (persistence.findById(user.getId()) != null) {
            persistence.update(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(User user) {
        if (persistence.findById(user.getId()) != null) {
            persistence.delete(user);
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAll() {
        return persistence.getAll();
    }

    /*
    public User findById(int id) {
        return persistence.findById(id);
    }
     */
}
