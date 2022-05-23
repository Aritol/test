package ua.org.learn.task.restaurant.service;

import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService instance = null;

    private UserService() {
        super();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void addUser(User user) {
        UserDao.createUser(user);
    }

    public List<User> getAllUsers() {
       return UserDao.getAllUsers();
    }

    public User getUserByLogin(String login) {
        try {
            User user = UserDao.getUserByLogin(login);
            if (user != null) {
                return user;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByLoginPassword(String login, String password) {
        User user = getUserByLogin(login);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void removeUserById(long id)  {
        UserDao.removeUserById(id);
    }

    public void updateUser(User user) {
        UserDao.updateUser(user);
    }
}
