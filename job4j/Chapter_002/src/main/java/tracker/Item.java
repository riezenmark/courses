package tracker;

public class Item {

    public Item() {
        super();    //calls parent class constructor (called implicitly)
        System.out.println("Item created");
    }

    public static void main(String[] args) {
        Bug bug= new Bug();
    }
}
