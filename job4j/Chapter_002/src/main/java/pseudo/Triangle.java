package pseudo;

public class Triangle implements Shape {

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
