package pseudo.factories;

import pseudo.shape.*;
import pseudo.shape.shapes.FilledSquare;
import pseudo.shape.shapes.Square;

/**
 * Factory of squares.
 */
public class SquareFactory implements ShapeFactory {

    /**
     * Creates square of given size.
     * @param size Size of square.
     * @return Square.
     */
    @Override
    public Shape create(int size) {
        return new Square(size);
    }

    /**
     * Creates filled square of given size.
     * @param size Size of square.
     * @return Filled square.
     */
    @Override
    public FilledShape createFilled(int size) {
        return new FilledSquare(size);
    }
}
