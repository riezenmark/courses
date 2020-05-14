package pseudo.shape;

/**
 * Base class for all shapes.
 */
public abstract class BaseShape implements Shape {
    /**
     * Shape size.
     */
    private final int size;

    /**
     * Creates shape of given size.
     * @param size Size of shape to create.
     */
    protected BaseShape(final int size) {
        this.size = size;
    }

    /**
     * Returns shape size.
     * @return Shape size.
     */
    public int getSize() {
        return size;
    }

}
