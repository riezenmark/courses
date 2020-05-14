package pseudo;

/**
 * Class for triangle to draw
 */
public class Triangle implements Shape {
    /**
     * Creates and returns String with triangle
     * @return String with triangle
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
