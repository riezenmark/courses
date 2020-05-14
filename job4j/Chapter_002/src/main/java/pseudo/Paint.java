package pseudo;

import pseudo.shape.Shape;

/**
 * Class for drawing shapes.
 */
public class Paint {
    /**
     * Draws shape.
     * @param shapes Any of shapes to draw.
     */
    public void draw(Shape... shapes) {
        for (Shape shape : shapes) {
            System.out.println(shape.draw());
        }
    }
}
