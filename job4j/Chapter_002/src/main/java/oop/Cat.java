package oop;

public class Cat {
    private String food;
    private String name;

    public void giveNick(String nick) {
        this.name = nick;
    }

    public String sound() {
        String voice = "may-may";
        return voice;
    }

    public void show() {
        System.out.println(this.name);
        System.out.println(this.food);
    }

    public void eat(String dish) {
        this.food = dish;
    }

    public static void main(String[] args) {
        Cat peppy = new Cat();
        String say = peppy.sound();
        System.out.println("Peppy says " + say);
        System.out.println("There is gav's food.");
        Cat gav = new Cat();
        gav.giveNick("Gav");
        gav.eat("kotleta");
        gav.show();
        System.out.println("There is black's food.");
        Cat black = new Cat();
        black.giveNick("Chernish");
        black.eat("fish");
        black.show();
    }
}
