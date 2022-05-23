package ua.org.learn.task.restaurant.dao;

import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.constant.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final static String SELECT_ALL_USERS = "SELECT * FROM USER";
    private final static String INSERT_USER = "INSERT INTO USER (IS_ACTIVE, LOGIN, NAME, PASSWORD, ROLE, SURNAME, UPDATED_BY, UPDATED_ON) " +
            "VALUES(?,?,?,?,?,?,?,?)";
    private final static String UPDATE_USER = "UPDATE USER SET IS_ACTIVE = ?, NAME = ?, PASSWORD = ?, ROLE = ?, SURNAME = ?, UPDATED_BY = ?, " +
            "UPDATED_ON = ? WHERE ID = ?";
    private final static String REMOVE_USER = "DELETE FROM USER WHERE ID =?";
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBUtil.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User createUser(User user) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setBoolean(1, user.getActive());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().toString());
            statement.setString(6, user.getSurname());
            statement.setString(7, user.getUpdatedBy());
            statement.setDate(8, user.getUpdatedOn());
            int changeLines = statement.executeUpdate();
            user = changeLines > 0 ? getUserByLogin(user.getLogin()) : null;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserById(long id) {
        return getAllUsers().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public static User getUserByLogin(String login) throws ClassNotFoundException, SQLException {
        return getAllUsers().stream().filter(user -> user.getLogin().equalsIgnoreCase(login)).findFirst().orElse(null);
    }

    public static void removeUserById(long id) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_USER)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setBoolean(1, user.getActive());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getSurname());
            statement.setString(6, user.getUpdatedBy());
            statement.setDate(7, user.getUpdatedOn());
            statement.setLong(8, user.getId());
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong(StringConstant.COLUMN_ID))
                .isActive(resultSet.getBoolean(StringConstant.COLUMN_IS_ACTIVE))
                .login(resultSet.getString(StringConstant.COLUMN_LOGIN))
                .name(resultSet.getString(StringConstant.COLUMN_NAME))
                .password(resultSet.getString(StringConstant.COLUMN_PASSWORD))
                .role(UserRole.getUserRoleByName(resultSet.getString(StringConstant.COLUMN_ROLE)))
                .surname(resultSet.getString(StringConstant.COLUMN_SURNAME))
                .updatedBy(resultSet.getString(StringConstant.COLUMN_UPDATED_BY))
                .updatedOn(resultSet.getDate(StringConstant.COLUMN_UPDATED_ON))
                .build();
    }
}
