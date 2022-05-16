import ua.org.learn.task.restaurant.dao.DBUtil;
import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.model.UserRole;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        User user = User.builder()
                .isActive(true)
                .login("test2")
                .name("Test")
                .password("12345")
                .role(UserRole.CUSTOMER)
                .surname("Customer")
                .updatedBy("admin").updatedOn(new Date(Instant.now().toEpochMilli()))
                .build();
        try {
            user = UserDao.createUser(user);
            System.out.println(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
