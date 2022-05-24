package ua.org.learn.task.restaurant.ui.main;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UiConstant;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.form.LoginForm;
import ua.org.learn.task.restaurant.ui.user.UserPanel;
import ua.org.learn.task.restaurant.ui.util.ImageUtil;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private static MainForm instance = null;

    private final JPanel foodPanel;
    private final MainMenuBar menuBar;
    private final JPanel orderPanel;
    private final MainFormStatusPanel statusPanel;
    private final UserPanel userPanel;

    private MainForm() {
        super();

        String iconPath = Configuration.getInstance().getImageProperty(StringConstant.PROPERTY_PATH_MAIN_ICON);
        if (iconPath != null) {
            Image formIcon = ImageUtil.getImage(iconPath);
            if (formIcon != null) {
                setIconImage(formIcon);
            }
        }
        setTitle(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FORM_MAIN));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new MainMenuBar(this);
        setJMenuBar(menuBar);

        orderPanel = new JPanel();

        foodPanel = new JPanel();

        userPanel = new UserPanel();

        statusPanel = new MainFormStatusPanel(this);
        add(statusPanel, BorderLayout.SOUTH);
    }

    public static MainForm getInstance() {
        if (instance == null) {
            instance = new MainForm();
        }
        return instance;
    }

    public void foodList() {
        clearForm();
        add(foodPanel, BorderLayout.CENTER);
        getRootPane().updateUI();
    }

    public void orderHistory() {
        clearForm();
        add(orderPanel, BorderLayout.CENTER);
        getRootPane().updateUI();
    }

    public void orderList() {
        clearForm();
        add(orderPanel, BorderLayout.CENTER);
        getRootPane().updateUI();
    }

    public void reloadBundle() {
        setTitle(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FORM_MAIN));
        menuBar.reloadBundle();
        statusPanel.reloadBundle();
        userPanel.reloadBundle();
    }

    public void updateUserList() {
        userPanel.modelUpdate();
    }

    public void setUser(User user) {
        if (user != null) {
            menuBar.setUserPermission(user);
            statusPanel.setUser(user);
            userPanel.setUser(user);
        }
    }

    public void userExit() {
        setVisible(false);
        LoginForm.getInstance().setVisible(true);
    }

    public void userList() {
        clearForm();
        add(userPanel, BorderLayout.CENTER);
        getRootPane().updateUI();
    }

    private void clearForm() {
        remove(foodPanel);
        remove(orderPanel);
        remove(userPanel);
    }
}
