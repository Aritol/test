package ua.org.learn.task.restaurant.ui.main;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UiConstant;
import ua.org.learn.task.restaurant.model.User;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class MainFormStatusPanel extends JPanel {
    private User currentUser;

    private final JLabel loggedLabel;

    public MainFormStatusPanel(JFrame frame) {
        super();
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(frame.getWidth(), UiConstant.UI_DEFAULT_HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        loggedLabel = new JLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_PANEL_STATUS_LOGGED));
        loggedLabel.setFont(UiConstant.FONT_ITALIC_16);
        loggedLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(loggedLabel);
    }

    public void reloadBundle() {
        loggedLabel.setText(String.format(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_PANEL_STATUS_LOGGED),
                currentUser.getName(),
                currentUser.getSurname()
        ));
    }

    public void setUser(User user) {
        currentUser = user;
        reloadBundle();
    }
}
