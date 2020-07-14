package cache;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCache {
    private final ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    public void add(Base model) {
        map.put(model.getId(), model);
    }

    public void update(Base model) throws OptimisticException {
        int currentVersion = model.getVersion();
        map.computeIfPresent(
                model.getId(), (integer, base) -> {
                    if (base.getVersion() != currentVersion) {
                        throw new OptimisticException("Model has already been changed.");
                    }
                    base.setVersion(base.getVersion() + 1);
                    return base;
                }
        );
    }

    public void delete(Base model) {
        map.remove(model.getId());
    }

    public int size() {
        return map.size();
    }

    public ConcurrentHashMap<Integer, Base> getCache() {
        return map;
    }
}
