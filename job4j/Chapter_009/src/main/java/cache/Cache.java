package cache;

import java.lang.ref.SoftReference;

public interface Cache<K, T> {
    SoftReference<T> getFromCache(K key);

    SoftReference<T> load(K key);
}
