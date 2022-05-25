import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.constant.UserRole;
import ua.org.learn.task.restaurant.ui.main.MainForm;

import java.util.Locale;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
//        JFrame frame = new JFrame("Test");
//        frame.getContentPane().add(new UserPanel());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setVisible(true);
        Configuration.getInstance().loadLanguagePack(new Locale("uk"));

        User user = User.builder().role(UserRole.ADMINISTRATOR).build();
        MainForm.getInstance().setUser(user);
//        MainForm.getInstance().reloadBundle();
        MainForm.getInstance().setVisible(true);
    }
}
