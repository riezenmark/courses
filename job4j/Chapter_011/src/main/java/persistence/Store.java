package persistence;

import models.User;

import java.util.List;

public interface Store {
    void add(User user);

    void update(User user);

    void delete(User user);

    List<User> getAll();

    User findById(int id);
}
