package cache;

import java.util.Objects;

public class Base {
    private final int id;
    private int version;

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getVersion() {
        return this.version;
    }

    void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id
                && version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
