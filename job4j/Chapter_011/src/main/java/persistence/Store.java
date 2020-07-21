package persistence;

import java.util.List;

public interface Store<T> {
    T add(T model);

    void update(T model);

    String delete(String id);

    List<T> getAll();

    T findById(String id);
}
