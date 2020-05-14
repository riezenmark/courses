package pseudo;

/**
 * Factory of shapes.
 */
public class ShapeFactory {

    /**
     * Creates shapes.
     * @param name Name of shape to create.
     * @return Shape.
     */
    public static Shape create(String name) {
        Shape shape = new EmptyShape();
        if ("Triangle".equals(name)) {
            shape = new Triangle();
        } else if ("Square".equals(name)) {
            shape = new Square();
        }
        return shape;
    }
}
