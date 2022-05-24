package ua.org.learn.task.restaurant.ui.util;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UiConstant;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UiComponentUtil {
    public static boolean checkFieldValue(Component parent, Object value, String name) {
        if (value == null || value.equals("")) {
            JOptionPane.showMessageDialog(
                    parent,
                    String.format(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_MESSAGE_FIELD_CANNOT_BE_EMPTY), name),
                    Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_WARNING),
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }
    public static JButton createButton(String name, ActionListener actionListener) {
        JButton button = new JButton(name);
        button.setFont(UiConstant.FONT_BOLD_16);
        button.addActionListener(actionListener);
        return button;
    }

    public static JCheckBox createCheckBox(String name) {
        JCheckBox checkBox = new JCheckBox(name);
        checkBox.setFont(UiConstant.FONT_PLAIN_16);
        return checkBox;
    }

    @SafeVarargs
    public static <T> JComboBox<T> createComboBox(T ... values) {
        JComboBox<T> comboBox = new JComboBox<>();
        Arrays.stream(values).forEach(comboBox::addItem);
        comboBox.setFont(UiConstant.FONT_PLAIN_16);
        return comboBox;
    }

    public static JLabel createLabel(String name) {
        JLabel label = new JLabel(name);
        label.setFont(UiConstant.FONT_BOLD_16);
        return label;
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

    public static JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(UiConstant.FONT_PLAIN_16);
        return field;
    }

    public static JSpinner createSpinner() {
        JSpinner spinner = new JSpinner();
        spinner.setFont(UiConstant.FONT_PLAIN_16);
        spinner.setValue(0.0);
        return spinner;
    }

    public static JTable createTable(TableModel tableModel) {
        JTable table = new JTable(tableModel);
        table.setFont(UiConstant.FONT_PLAIN_16);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }

    public static JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(UiConstant.FONT_PLAIN_16);
        return field;
    }

    public static void locateComponent(JFrame form, GridBagLayout layout, GridBagConstraints constraint, JLabel label, Component field) {
        constraint.weightx = 1;
        constraint.gridwidth = GridBagConstraints.RELATIVE;
        layout.setConstraints(label, constraint);
        form.add(label);

        constraint.weightx = 3;
        constraint.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(field, constraint);
        form.add(field);
    }
}
