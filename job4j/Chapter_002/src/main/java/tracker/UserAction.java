package tracker;

public interface UserAction {

    int key();

    boolean execute(Input input, TrackerSingleton tracker);

    String info();

}
