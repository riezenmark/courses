package persistence;

public interface UserStore<T> extends Store<T> {
    boolean isCredential(String login, String password);

    int getRoleID(String login, String password);

    int getUserID(String login, String password);
}
