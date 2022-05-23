package ua.org.learn.task.restaurant.ui.main;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UiConstant;
import ua.org.learn.task.restaurant.model.User;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainMenuBar extends JMenuBar {
    private final Configuration configuration;

    private final JMenuItem foodListItem;
    private final JMenu foodMenu;
    private final JMenuItem orderHistoryItem;
    private final JMenuItem orderListItem;
    private final JMenu orderMenu;
    private final JMenuItem userExitItem;
    private final JMenuItem userListItem;
    private final JMenu userMenu;

    public MainMenuBar(MainForm form) {
        super();
        setFont(UiConstant.FONT_COMMON);

        configuration = Configuration.getInstance();

        orderMenu = createMenu(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER));
        add(orderMenu);

        orderListItem = createMenuItem(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER_LIST), event -> form.orderList());
        orderMenu.add(orderListItem);

        orderHistoryItem = createMenuItem(configuration.getBundleProperty(
                StringConstant.BUNDLE_LABEL_MENU_ORDER_HISTORY),
                event -> form.orderHistory()
        );
        orderMenu.add(orderHistoryItem);

        foodMenu = createMenu(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_FOOD));
        add(foodMenu);

        foodListItem = createMenuItem(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_FOOD_LIST), event -> form.foodList());
        foodMenu.add(foodListItem);

        userMenu = createMenu(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER));
        add(userMenu);

        userListItem = createMenuItem(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER_LIST), event -> form.userList());
        userMenu.add(userListItem);

        userExitItem = createMenuItem(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER_EXIT), event -> form.userExit());
        userMenu.add(userExitItem);
    }

    public void setUserPermission(User user) {
        if (user != null) {
            switch (user.getRole()) {
                case ADMINISTRATOR:
                    foodMenu.setEnabled(true);
                    orderMenu.setEnabled(true);
                    userListItem.setEnabled(true);
                    break;
                case CUSTOMER:
                case MANAGER:
                    foodMenu.setEnabled(false);
                    foodMenu.setEnabled(true);
                    userListItem.setEnabled(false);
                    break;
                default:
                    foodMenu.setEnabled(false);
                    foodMenu.setEnabled(false);
                    userListItem.setEnabled(false);
                    break;
            }
        }
    }

    public void reloadBundle() {
        foodMenu.setText(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_FOOD));
        foodListItem.setText(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_FOOD_LIST));
        orderMenu.setText(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER));
        orderHistoryItem.setText(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER_HISTORY));
        orderListItem.setText(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER_LIST));
        userMenu.setText(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER));
        userExitItem.setText(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER_LIST));
        userListItem.setText(configuration.getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER_EXIT));
    }

    private JMenu createMenu(String name) {
        JMenu menu = new JMenu(name);
        menu.setFont(UiConstant.FONT_COMMON);
        return menu;
    }

    private JMenuItem createMenuItem(String name, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setFont(UiConstant.FONT_COMMON);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }
}
