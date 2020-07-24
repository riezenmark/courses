package models;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class User {
    private final int id;
    private String name;
    private String login;
    private String email;
    private String password;
    private Role role;
    private final Calendar createDate;

    public User(
            int id,
            String name,
            String login,
            String email,
            String password,
            int roleId,
            Calendar createDate
    ) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = Role.getRole(roleId);
        this.createDate = createDate;
    }

    public User(
            int id,
            String name,
            String login,
            String email,
            String password,
            Role role,
            Calendar createDate
    ) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createDate = createDate;
    }

    public User(
            int id,
            String name,
            String login,
            String email,
            String password,
            String roleName,
            long time
    ) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = Role.getRole(roleName);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        this.createDate = calendar;
    }

    public User(int id) {
        this.id = id;
        createDate = Calendar.getInstance();
    }

    public String getId() {
        return String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Date getCreateDate() {
        return createDate.getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email)
                && Objects.equals(createDate, user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createDate);
    }
}
