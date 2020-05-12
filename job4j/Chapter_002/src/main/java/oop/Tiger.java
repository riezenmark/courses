package oop;

public class Tiger extends Predator {
    public Tiger(String name) {
        super(name);
        System.out.println("Tiger object created.");
    }

    @Override               //annotation to show that programmer remembers that there were such method in parent class
    public void voice() {   //overrides Predator.voice()
        System.out.println("Roar!");
    }

    public static void main(String[] args) {
        Tiger tiger = new Tiger("Tiger");

        tiger.voice();  //"Roar!" instead of "Grrr!"
    }
}
