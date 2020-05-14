package pseudo;

/**
 * Class for square to draw
 */
public class Square implements Shape {
    /**
     * Creates and returns String with square
     * @return String with square
     */
    @Override
    public String draw() {
        StringBuilder picture = new StringBuilder();
        picture.append("+------+");
        picture.append("|      |");
        picture.append("|      |");
        picture.append("+------+");
        return picture.toString();
    }
}
