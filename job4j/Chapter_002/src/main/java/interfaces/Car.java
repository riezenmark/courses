package interfaces;

public interface Car {

    public default void gas() {
        System.out.println("Газ!");
    }

    public default void brake() {
        System.out.println("Тормоз!");
    }
}
