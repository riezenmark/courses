package models;

public enum Role {
    ADMIN(1, "admin"),
    GUEST(2, "guest");

    private final int id;
    private final String name;

    Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Role getRole(int roleId) {
        if (roleId == 1) {
            return ADMIN;
        } else {
            return GUEST;
        }
    }

    public static Role getRole(String roleName) {
        if (roleName.endsWith("admin")) {
            return ADMIN;
        } else {
            return GUEST;
        }
    }
}
