package pseudo.shape.shapes;

import pseudo.shape.BaseShape;

/**
 * Class for triangle to draw.
 */
public class Triangle extends BaseShape {

    /**
     * Creates triangle of size 1.
     */
    public Triangle() {
        super(1);
    }

    /**
     * Creates triangle of given size.
     * @param size Size of triangle.
     */
    public Triangle(int size) {
        super(size);
    }

    /**
     * Creates and returns String with triangle.
     * @return String with triangle.
     */
    @Override
    public String draw() {
        StringBuilder picture = new StringBuilder();
        picture.append("   +   ");
        picture.append("  + +  ");
        picture.append(" +   + ");
        picture.append("+++++++");
        return picture.toString();
    }
}
