package ua.org.learn.task.restaurant.model;

import ua.org.learn.task.restaurant.constant.StringConstant;

import java.time.Instant;

public class Order {
    private long id;
    private Boolean isActive;
    private String login;
    private String name;
    private String password;
    private UserRole role;
    private String surname;
    private String updatedBy;
    private Instant updatedOn;

    public long getId() {
        return id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public String getSurname() {
        return surname;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public Order setId(long id) {
        this.id = id;
        return this;
    }

    public Order setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Order setName(String name) {
        this.name = name;
        return this;
    }

    public Order setLogin(String login) {
        this.login = login;
        return this;
    }

    public Order setPassword(String password) {
        this.password = password;
        return this;
    }

    public Order setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public Order setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Order setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Order setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        return String.format(StringConstant.USER_TO_STRING, id, name, surname, login, password, role, updatedBy, updatedOn, isActive);
    }

    public static class UserBuilder {
        private Order user;

        public UserBuilder() {
            user = new Order();
        }

        public UserBuilder id(Long id) {
            user.setId(id);
            return this;
        }

        public UserBuilder isActive(Boolean isActive) {
            user.setActive(isActive);
            return this;
        }

        public UserBuilder login(String login) {
            user.setLogin(login);
            return this;
        }

        public UserBuilder name(String name) {
            user.setName(name);
            return this;
        }

        public UserBuilder password(String password) {
            user.setPassword(password);
            return this;
        }

        public UserBuilder role(UserRole role) {
            user.setRole(role);
            return this;
        }

        public UserBuilder surname(String surname) {
            user.setSurname(surname);
            return this;
        }

        public UserBuilder updatedBy(String updatedBy) {
            user.setUpdatedBy(updatedBy);
            return this;
        }

        public UserBuilder updatedOn(Instant updatedOn) {
            user.setUpdatedOn(updatedOn);
            return this;
        }

        public Order build() {
            return user;
        }
    }
}
