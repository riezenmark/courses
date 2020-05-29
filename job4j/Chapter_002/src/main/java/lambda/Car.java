package lambda;

public interface Car {

    default void gas() {
        System.out.println("Газ!");
    }

    default void brake() {
        System.out.println("Тормоз!");
    }
}
