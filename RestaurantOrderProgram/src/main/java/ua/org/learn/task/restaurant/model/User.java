package ua.org.learn.task.restaurant.model;

import ua.org.learn.task.restaurant.constant.StringConstant;

import java.sql.Date;

public class User {
    private long id;
    private boolean isActive;
    private String login;
    private String name;
    private String password;
    private UserRole role;
    private String surname;
    private String updatedBy;
    private Date updatedOn;

    public long getId() {
        return id;
    }

    public boolean getActive() {
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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public User setActive(boolean active) {
        isActive = active;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public User setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public User setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        return String.format(StringConstant.TO_STRING_USER, id, name, surname, login, password, role, updatedBy, updatedOn, isActive);
    }

    public static class UserBuilder {
        private User user;

        public UserBuilder() {
            user = new User();
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

        public UserBuilder updatedOn(Date updatedOn) {
            user.setUpdatedOn(updatedOn);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
