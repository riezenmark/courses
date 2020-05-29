package lambda;

public class Duck implements FlyingBird, Waterfowl {

    //методы обоих интерфейсов легко объединяются в одном классе

    @Override
    public void fly() {
        System.out.println("Летим!");
    }

    @Override
    public void swim() {
        System.out.println("Плывем!");
    }
}
