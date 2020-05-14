package pseudo.shape.shapes;

import pseudo.shape.BaseFilledShape;

/**
 * Class for filled triangle to draw.
 */
public class FilledTriangle extends BaseFilledShape {

    /**
     * Creates filled triangle of size 1.
     */
    public FilledTriangle() {
        super(1);
    }

    /**
     * Creates filled triangle of given size.
     * @param size Size of triangle.
     */
    public FilledTriangle(int size) {
        super(size);
    }

    /**
     * Creates and returns String with filled triangle.
     * @return String with filled triangle.
     */
    @Override
    public String draw() {
        StringBuilder picture = new StringBuilder();
        picture.append("   +   ");
        picture.append("  +++  ");
        picture.append(" +++++ ");
        picture.append("+++++++");
        return picture.toString();
    }
}
