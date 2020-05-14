package pseudo.shape.shapes;

import pseudo.shape.BaseShape;

/**
 * Class for square to draw.
 */
public class Square extends BaseShape {

    /**
     * Creates square of size 1.
     */
    public Square() {
        super(1);
    }

    /**
     * Creates square of given size.
     * @param size Size of square.
     */
    public Square(int size) {
        super(size);
    }

    /**
     * Creates and returns String with square.
     * @return String with square.
     */
    @Override
    public String draw() {
        StringBuilder picture = new StringBuilder();
        picture.append("++++++++");
        picture.append("+      +");
        picture.append("+      +");
        picture.append("++++++++");
        return picture.toString();
    }
}
