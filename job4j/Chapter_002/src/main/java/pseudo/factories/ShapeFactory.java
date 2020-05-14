package pseudo.factories;

import pseudo.shape.*;
import pseudo.shape.shapes.EmptyShape;

/**
 * An abstract factory for different shapes.
 */
public interface ShapeFactory {

    /**
     * Creates empty shape.
     * @return Empty shape.
     */
    default EmptyShape createEmpty() {
        return new EmptyShape();
    }

    /**
     * Creates shape of given size.
     * @param size Size of shape.
     * @return Shape.
     */
    Shape create(int size);

    /**
     * Creates filled shape of given size.
     * @param size Size of shape.
     * @return Filled shape.
     */
    FilledShape createFilled(int size);

}
