package pseudo;

/**
 * Class for drawing shapes
 */
public class Paint {
    /**
     * Draws shape
     * @param shape Any of shapes to draw
     */
    public void draw(Shape shape) {
        System.out.print(shape.draw());
    }
}
