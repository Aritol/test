package ua.org.learn.task.restaurant.model;

public enum UserRole {
    USER,
    MANAGER,
    ADMINISTRATOR,
    UNDEFINED;

    public UserRole getUserRoleByName(String name) {
        try {
            return UserRole.valueOf(name);
        } catch (Exception e) {
            return UNDEFINED;
        }
    }
}
