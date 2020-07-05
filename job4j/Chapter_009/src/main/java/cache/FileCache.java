package cache;

import java.io.File;
import java.lang.ref.SoftReference;

public class FileCache<K, T> extends AbstractCache<K, T> {

    @Override
    public boolean hasKey(K key, T object) {
        return key.equals(((File) object).getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public SoftReference<T> load(K filename) {
        SoftReference<T> reference = null;
        try {
            File file = new File((String) filename);
            reference = new SoftReference<>((T) file);
            list.add(reference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reference;
    }
}
