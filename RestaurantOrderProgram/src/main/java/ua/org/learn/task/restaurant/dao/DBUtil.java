package ua.org.learn.task.restaurant.dao;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DBUtil {
    private static DBUtil instance = null;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    private DBUtil() throws ClassNotFoundException {
        Configuration configuration = Configuration.getInstance();
        dbUrl = configuration.getCommonProperty(StringConstant.PROPERTY_DB_URL);
        dbUser = configuration.getCommonProperty(StringConstant.PROPERTY_DB_USER);
        dbPassword = configuration.getCommonProperty(StringConstant.PROPERTY_DB_PASSWORD);
        Class.forName(configuration.getCommonProperty(StringConstant.PROPERTY_DB_DRIVER));
    }

    public static DBUtil getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DBUtil();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

}
