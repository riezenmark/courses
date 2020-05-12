package oop;

public class Tiger extends Predator {
    public Tiger(String name) {
        super(name);
        System.out.println("Tiger object created.");
    }

    public void voice() {   //overrides Predator.voice()
        System.out.println("Roar!");
    }

    public static void main(String[] args) {
        Tiger tiger = new Tiger("Tiger");

        tiger.voice();  //"Roar!" instead of "Grrr!"
    }
}
