package persistence;

import models.User;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ThreadSafe
public enum MemoryStore implements Store<User> {
    INSTANCE;

    private final List<User> users = new CopyOnWriteArrayList<>();

    @Override
    public User add(User user) {
        this.users.add(user);
        return user;
    }

    @Override
    public void update(User user) {
        this.update(user, this.findById(String.valueOf(user.getId())));
    }

    @Override
    public String delete(String id) {
        users.remove(this.findById(id));
        return "DELETED";
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User findById(String id) {
        for (var u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    private void update(User from, User to) {
        if (from.getName() != null && !from.getName().equals("")) {
            to.setName(from.getName());
        }
        if (from.getLogin() != null && !from.getLogin().equals("")) {
            to.setLogin(from.getLogin());
        }
        if (from.getEmail() != null && !from.getEmail().equals("")) {
            to.setEmail(from.getEmail());
        }
    }
}
