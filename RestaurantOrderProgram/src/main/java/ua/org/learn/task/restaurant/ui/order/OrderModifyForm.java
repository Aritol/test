package ua.org.learn.task.restaurant.ui.order;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UserRole;
import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.model.Order;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.food.FoodPanel;
import ua.org.learn.task.restaurant.ui.food.assignment.FoodAssignmentPanel;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.ui.main.MainFormStatusPanel;
import ua.org.learn.task.restaurant.ui.main.MainMenuBar;
import ua.org.learn.task.restaurant.ui.user.UserPanel;
import ua.org.learn.task.restaurant.ui.util.ImageUtil;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.time.Instant;

public class OrderModifyForm extends JFrame {
    private static OrderModifyForm instance = null;

    private ModifyType modifyType;

    private final FoodAssignmentPanel foodAssignmentPanel;
    private final OrderDetailPanel orderDetailPanel;

    public OrderModifyForm() {
        super();

        String iconPath = Configuration.getInstance().getImageProperty(StringConstant.PROPERTY_PATH_MAIN_ICON);
        if (iconPath != null) {
            Image formIcon = ImageUtil.getImage(iconPath);
            if (formIcon != null) {
                setIconImage(formIcon);
            }
        }
        setSize(1000, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        foodAssignmentPanel = new FoodAssignmentPanel();
        add(foodAssignmentPanel, BorderLayout.WEST);

        orderDetailPanel = new OrderDetailPanel();
        add(orderDetailPanel, BorderLayout.EAST);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                closeForm();
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

    public static OrderModifyForm getInstance() {
        if (instance == null) {
            instance = new OrderModifyForm();
        }
        return instance;
    }

    public void modify() {
        orderDetailPanel.modify();
    }

    public void reloadBundle() {
        setTitle(Configuration.getInstance().getBundleProperty(modifyType == ModifyType.DETAIL
                ? StringConstant.BUNDLE_LABEL_FORM_ORDER_DETAIL
                : StringConstant.BUNDLE_LABEL_FORM_ORDER_EDIT));
        foodAssignmentPanel.reloadBundle();
    }

    public void setModifyType(ModifyType type) {
        clearForm();
        modifyType = type;
        switch (modifyType) {
            case DETAIL -> add(orderDetailPanel, BorderLayout.CENTER);
            case EDIT -> {
                add(foodAssignmentPanel, BorderLayout.WEST);
                add(orderDetailPanel, BorderLayout.EAST);
            }
        }
        reloadBundle();
        getRootPane().updateUI();
    }

    public void setOrder(Order order) {
        foodAssignmentPanel.setOrder(order);
        orderDetailPanel.setOrder(order);
    }

    public void setUser(User user) {
        foodAssignmentPanel.setUser(user);
        orderDetailPanel.setUser(user);
    }

    private void closeForm() {
        if (modifyType == ModifyType.EDIT) {

        }
        MainForm.getInstance().setVisible(true);
    }

    private void clearForm() {
        remove(foodAssignmentPanel);
        remove(orderDetailPanel);
    }
}
