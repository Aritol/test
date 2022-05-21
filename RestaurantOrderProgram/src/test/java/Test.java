import ua.org.learn.task.restaurant.ui.user.UserPanel;

import javax.swing.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        JFrame frame = new JFrame("Test");
        frame.getContentPane().add(new UserPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
