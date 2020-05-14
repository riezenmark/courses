package pseudo.factories;

import pseudo.shape.*;
import pseudo.shape.shapes.FilledTriangle;
import pseudo.shape.shapes.Triangle;

/**
 * Factory of triangles.
 */
public class TriangleFactory implements ShapeFactory {

    /**
     * Creates triangle of given size.
     * @param size Size of triangle.
     * @return Triangle.
     */
    @Override
    public Shape create(int size) {
        return new Triangle(size);
    }

    /**
     * Creates filled triangle of given size.
     * @param size Size of triangle.
     * @return Filled triangle.
     */
    @Override
    public FilledShape createFilled(int size) {
        return new FilledTriangle(size);
    }

}
