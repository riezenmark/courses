package generic;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private final int length;
    private final SimpleArray<T> array;

    @SuppressWarnings("unchecked")
    public AbstractStore(final int length) {
        this.length = length;
        this.array = new SimpleArray<>((Class<T>) Base.class, length);
    }

    @Override
    public void add(T model) {
        this.array.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int i = findIndexById(id);
        if (i != -1) {
            this.array.set(i, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int i = findIndexById(id);
        if (i != -1) {
            this.array.remove(i);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int i = findIndexById(id);
        if (i != -1) {
            return this.array.get(i);
        }
        return null;
    }

    private int findIndexById(String id) {
        for (int i = 0; i < this.length; i++) {
            if (this.array.get(i) != null && this.array.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
