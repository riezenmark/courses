package logic;

import models.Role;
import models.User;
import persistence.DBStore;
import persistence.UserStore;

import java.util.Calendar;
import java.util.List;

public class ValidateService {
    private static final ValidateService INSTANCE = new ValidateService();
    private final Object lock = new Object();
    private volatile int count;

    private final UserStore<User> persistence = DBStore.getInstance();

    public ValidateService() {
        this.count = persistence.size() + 1;
        createRoot();
        synchronized (lock) {
            count++;
        }
        addGuest();
        synchronized (lock) {
            count++;
        }
    }

    private void createRoot() {
        persistence.add(
                new User(
                        count,
                        "root",
                        "root",
                        "root@mail.com",
                        "root",
                        Role.ADMIN,
                        Calendar.getInstance()
                )
        );
    }

    private void addGuest() {
        persistence.add(
                new User(
                        count,
                        "guest",
                        "guest",
                        "guest@mail.com",
                        "guest",
                        Role.GUEST,
                        Calendar.getInstance()
                )
        );
    }

    public static ValidateService getINSTANCE() {
        return INSTANCE;
    }

    public void add(User user) {
        if (persistence.findById(user.getId()) == null) {
            persistence.add(user);
            synchronized (lock) {
                count++;
            }
        }
    }

    public void update(User user) {
        if (persistence.findById(user.getId()) != null) {
            persistence.update(user);
        }
    }

    public void delete(User user) {
        if (persistence.findById(user.getId()) != null) {
            persistence.delete(user.getId());
        }
    }

    public int count() {
        return this.count;
    }

    public List<User> getAll() {
        return persistence.getAll();
    }

    public User findById(int id) {
        return persistence.findById(String.valueOf(id));
    }

    public boolean isCredential(String login, String password) {
        return persistence.isCredential(login, password);
    }

    public int getRoleID(String login, String password) {
        return persistence.getRoleID(login, password);
    }

    public int getUserID(String login, String password) {
        return persistence.getUserID(login, password);
    }
}
