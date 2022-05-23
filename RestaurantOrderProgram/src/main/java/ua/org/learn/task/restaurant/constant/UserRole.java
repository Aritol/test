package ua.org.learn.task.restaurant.constant;

public enum UserRole {
    CUSTOMER,
    MANAGER,
    ADMINISTRATOR,
    UNDEFINED;

    public static UserRole getUserRoleByName(String name) {
        try {
            return UserRole.valueOf(name);
        } catch (Exception e) {
            return UNDEFINED;
        }
    }
}
