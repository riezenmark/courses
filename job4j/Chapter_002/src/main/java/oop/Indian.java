package oop;

public class Indian extends Tiger {
    public Indian (String name) {
        super(name);
    }

    public static void main(String[] args) {
        Indian tiger = new Indian("Sher'khan");
        tiger.voice();  //inherits overrided method from Tiger instead of Predator
    }
}
