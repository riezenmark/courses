package oop;

public class Predator extends Animal {
    public Predator(String name) {
        super(name);
        System.out.println("Predator object created.");
    }

    public void voice() {
        System.out.println("Grrr!");
    }
}
