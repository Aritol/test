package ua.org.learn.task.restaurant.ui.main;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UiConstant;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {
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
        setFont(UiConstant.FONT_BOLD_16);

        orderMenu = UiComponentUtil.createMenu(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER));
        add(orderMenu);

        orderListItem = UiComponentUtil.createMenuItem(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER_LIST),
                event -> form.orderList()
        );
        orderMenu.add(orderListItem);

        orderHistoryItem = UiComponentUtil.createMenuItem(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER_HISTORY),
                event -> form.orderHistory()
        );
        orderMenu.add(orderHistoryItem);

        foodMenu = UiComponentUtil.createMenu(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_FOOD));
        add(foodMenu);

        foodListItem = UiComponentUtil.createMenuItem(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_FOOD_LIST),
                event -> form.foodList()
        );
        foodMenu.add(foodListItem);

        userMenu = UiComponentUtil.createMenu(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER));
        add(userMenu);

        userListItem = UiComponentUtil.createMenuItem(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER_LIST),
                event -> form.userList()
        );
        userMenu.add(userListItem);

        userExitItem = UiComponentUtil.createMenuItem(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER_EXIT),
                event -> form.userExit()
        );
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
                    orderMenu.setEnabled(true);
                    userListItem.setEnabled(false);
                    break;
                default:
                    foodMenu.setEnabled(false);
                    orderMenu.setEnabled(false);
                    userListItem.setEnabled(false);
                    break;
            }
        }
    }

    public void reloadBundle() {
        foodMenu.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_FOOD));
        foodListItem.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_FOOD_LIST));
        orderMenu.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER));
        orderHistoryItem.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER_HISTORY));
        orderListItem.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_ORDER_LIST));
        userMenu.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER));
        userExitItem.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER_LIST));
        userListItem.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_MENU_USER_EXIT));
    }
}
