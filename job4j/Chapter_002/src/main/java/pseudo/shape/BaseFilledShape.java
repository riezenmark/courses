package pseudo.shape;

/**
 * Base class for filled shapes.
 */
public abstract class BaseFilledShape extends BaseShape implements FilledShape {

    /**
     * Creates filled shape of given size.
     * @param size Size of shape to create.
     */
    protected BaseFilledShape(final int size) {
        super(size);
    }
}
