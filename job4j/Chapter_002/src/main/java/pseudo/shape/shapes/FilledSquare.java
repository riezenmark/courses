package pseudo.shape.shapes;

import pseudo.shape.BaseFilledShape;

/**
 * Class for filled square to draw.
 */
public class FilledSquare extends BaseFilledShape {

    /**
     * Creates filled square of size 1.
     */
    public FilledSquare() {
        super(1);
    }

    /**
     * Creates filled square of given size.
     * @param size Size of square.
     */
    public FilledSquare(int size) {
        super(size);
    }

    /**
     * Creates and returns String with filled square.
     * @return String with filled square.
     */
    @Override
    public String draw() {
        StringBuilder picture = new StringBuilder();
        picture.append("++++++++");
        picture.append("++++++++");
        picture.append("++++++++");
        picture.append("++++++++");
        return picture.toString();
    }
}
