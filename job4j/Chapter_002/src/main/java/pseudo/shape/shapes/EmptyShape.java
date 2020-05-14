package pseudo.shape.shapes;

import pseudo.shape.BaseShape;

/**
 * Class for empty shapes.
 */
public class EmptyShape extends BaseShape {

    /**
     * Creates shape with zero size.
     */
    public EmptyShape() {
        super(0);
    }

    /**
     * Draws empty shape.
     * @return null.
     */
    @Override
    public String draw() {
        return null;
    }

}
