package cache;

import java.lang.ref.SoftReference;

public interface Cache<K, T> {
    boolean hasKey(K key, T object);

    SoftReference<T> getFromCache(K key);

    SoftReference<T> load(K key);
}
