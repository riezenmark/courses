package cache;

import java.lang.ref.SoftReference;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class AbstractCache<K, T> implements Cache<K, T> {
    private SoftReference<T> cache;
    private final Function<K, T> create;
    private final BiFunction<K, T, Boolean> check;

    public AbstractCache(Function<K, T> create, BiFunction<K, T, Boolean> check) {
        this.create = create;
        this.check = check;
    }

    private boolean hasKey(K key, SoftReference<T> reference) {
        return reference != null && check.apply(key, reference.get());
    }

    @Override
    public SoftReference<T> getFromCache(K key) {
        if (hasKey(key, this.cache)) {
            return this.cache;
        }
        return load(key);
    }

    @Override
    public SoftReference<T> load(K key) {
        try {
            cache = new SoftReference<>(this.create.apply(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }
}
