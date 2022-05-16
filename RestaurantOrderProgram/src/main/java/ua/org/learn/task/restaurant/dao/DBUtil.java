package ua.org.learn.task.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DBUtil {
    private static DBUtil instance = null;

//    private final String dbDriver;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    private DBUtil() throws ClassNotFoundException {
        ResourceBundle bundle = ResourceBundle.getBundle("restaurant");
        dbUrl = bundle.getString("db.url");
//        dbDriver = bundle.getString("db.driver");
        dbUser = bundle.getString("db.user");
        dbPassword = bundle.getString("db.password");
        Class.forName(bundle.getString("db.driver"));
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
