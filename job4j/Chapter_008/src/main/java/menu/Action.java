package menu;

public class Action {
    private final String name;

    public Action(final String name) {
        this.name = name;
    }

    public void execute() {
        System.out.println(name);
    }
}
