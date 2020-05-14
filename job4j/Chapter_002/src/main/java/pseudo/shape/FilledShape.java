package pseudo.shape;

/**
 * Interface for filled shapes.
 */
public interface FilledShape extends Shape {
    default String isFilled() {
        return "It is a Filled Shape.";
    }
}
