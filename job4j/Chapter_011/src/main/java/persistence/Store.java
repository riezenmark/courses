package persistence;

import java.util.List;

public interface Store<T> {
    int size();

    void add(T model);

    void update(T model);

    void delete(String id);

    List<T> getAll();

    T findById(String id);
}
