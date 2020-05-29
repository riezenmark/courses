package lambda;

public class UnusualCar implements Car {
    @Override
    public void gas() {
        System.out.println("Эта машина газует по-другому!");
    }

    @Override
    public void brake() {
        System.out.println("Эта машина тормозит по-другому!");
    }
}
