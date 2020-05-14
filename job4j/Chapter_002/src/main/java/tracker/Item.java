package tracker;

import java.util.Objects;

/**
 * Class for requests.
 */
public class Item {
    /**
     * Request id.
     */
    private String id;
    /**
     * Request name.
     */
    private String name;

    /**
     * Creates a request with given name.
     * @param name Name of request.
     */
    public Item(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
