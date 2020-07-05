package cache;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public abstract class AbstractCache<K, T> implements Cache<K, T> {
    protected final ArrayList<SoftReference<T>> list = new ArrayList<>();

    @Override
    public SoftReference<T> getFromCache(K key) {
        for (SoftReference<T> reference : list) {
            if (hasKey(key, reference.get())) {
                return reference;
            }
        }
        return load(key);
    }
}
