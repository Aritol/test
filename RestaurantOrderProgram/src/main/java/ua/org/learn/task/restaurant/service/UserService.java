package ua.org.learn.task.restaurant.service;

import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.exception.BusinessException;
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

    public List<User> getAllUsers() throws BusinessException {
        try {
            return UserDao.getAllUsers();
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Can't load data from database");
        }
    }

    public User getUserByLogin(String login) throws BusinessException {
        try {
            User user = UserDao.getUserByLogin(login);
            if (user != null) {
                return user;
            } else {
                throw new BusinessException("User not found");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Can't load data from database");
        }
    }

    public User getUserByLoginPassword(String login, String password) throws BusinessException {
        User user = getUserByLogin(login);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new BusinessException("Password incorrect");
        }
    }

    public void removeUserById(long id) throws BusinessException {
        try {
            UserDao.removeUserById(id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException("Can't load data from database");
        }
    }
}
