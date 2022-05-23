package ua.org.learn.task.restaurant.ui.util;

import ua.org.learn.task.restaurant.constant.UiConstant;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UiComponentUtil {
    public static JButton createButton(String name, ActionListener actionListener) {
        JButton button = new JButton(name);
        button.setFont(UiConstant.FONT_BOLD_16);
        button.addActionListener(actionListener);
        return button;
    }

    public static JMenu createMenu(String name) {
        JMenu menu = new JMenu(name);
        menu.setFont(UiConstant.FONT_BOLD_16);
        return menu;
    }

    public static JMenuItem createMenuItem(String name, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setFont(UiConstant.FONT_BOLD_16);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }
}
