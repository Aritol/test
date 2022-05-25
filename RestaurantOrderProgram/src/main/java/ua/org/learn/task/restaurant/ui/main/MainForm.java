package ua.org.learn.task.restaurant.ui.main;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UiConstant;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.food.FoodPanel;
import ua.org.learn.task.restaurant.ui.form.LoginForm;
import ua.org.learn.task.restaurant.ui.order.OrderPanel;
import ua.org.learn.task.restaurant.ui.user.UserPanel;
import ua.org.learn.task.restaurant.ui.util.ImageUtil;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainForm extends JFrame {
    private static MainForm instance = null;

    private final FoodPanel foodPanel;
    private final MainMenuBar menuBar;
    private final OrderPanel orderPanel;
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

        menuBar = new MainMenuBar(this);
        setJMenuBar(menuBar);

        orderPanel = new OrderPanel();

        foodPanel = new FoodPanel();

        userPanel = new UserPanel();

        statusPanel = new MainFormStatusPanel(this);
        add(statusPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                userExit();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
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
        orderPanel.setHistoryMode(true);
        add(orderPanel, BorderLayout.CENTER);
        getRootPane().updateUI();
    }

    public void orderList() {
        clearForm();
        orderPanel.setHistoryMode(false);
        add(orderPanel, BorderLayout.CENTER);
        getRootPane().updateUI();
    }

    public void reloadBundle() {
        setTitle(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FORM_MAIN));
        foodPanel.reloadBundle();
        menuBar.reloadBundle();
        orderPanel.reloadBundle();
        statusPanel.reloadBundle();
        userPanel.reloadBundle();
    }

    public void setUser(User user) {
        if (user != null) {
            foodPanel.setUser(user);
            menuBar.setUserPermission(user);
            orderPanel.setUser(user);
            statusPanel.setUser(user);
            userPanel.setUser(user);
        }
    }

    public void updateFoodList() {
        foodPanel.modelUpdate();
    }

    public void updateUserList() {
        userPanel.modelUpdate();
    }

    public void userExit() {
        clearForm();
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
