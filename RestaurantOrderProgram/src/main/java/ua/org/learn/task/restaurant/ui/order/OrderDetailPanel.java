package ua.org.learn.task.restaurant.ui.order;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.FoodAssignmentDao;
import ua.org.learn.task.restaurant.dao.FoodDao;
import ua.org.learn.task.restaurant.model.FoodAssignment;
import ua.org.learn.task.restaurant.model.Order;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.food.assignment.AssignmentPanel;
import ua.org.learn.task.restaurant.ui.food.assignment.FoodAssignmentTableModel;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.Instant;

public class OrderDetailPanel extends JPanel {
    private Order selectedOrder;

    private final AssignmentPanel assignmentPanel;
    private final JTextField gratuityField;
    private final JLabel gratuityLabel;
    private final JButton printCheckButton;
    private final JTextField tableNumberField;
    private final JLabel tableLabel;
    private final JTextField totalField;
    private final JLabel totalLabel;

    public OrderDetailPanel() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        assignmentPanel = new AssignmentPanel();
        add(assignmentPanel);

        JPanel orderDetailPanel = new JPanel();
        add(orderDetailPanel);

        GridBagLayout orderDetailLayout = new GridBagLayout();
        orderDetailPanel.setLayout(orderDetailLayout);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;

        tableLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_TABLE_NUMBER));
        tableNumberField = UiComponentUtil.createTextField();
        UiComponentUtil.locateComponent(orderDetailPanel, orderDetailLayout, constraint, tableLabel, tableNumberField);

        gratuityLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_GRATUITY));
        gratuityField = UiComponentUtil.createTextField();
        UiComponentUtil.locateComponent(orderDetailPanel, orderDetailLayout, constraint, gratuityLabel, gratuityField);

        totalLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_TOTAL));
        totalField = UiComponentUtil.createTextField();
        totalField.setEnabled(false);
        UiComponentUtil.locateComponent(orderDetailPanel, orderDetailLayout, constraint, totalLabel, totalField);

        JPanel userActionPanel = new JPanel();
        add(userActionPanel);

        printCheckButton = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_CHECK_PRINT),
                event -> {
                    if (selectedOrder != null) {
                    }
                }
        );
        userActionPanel.add(printCheckButton);
    }

    public void modify() {
        assignmentPanel.modify();
        setTotal();
    }

    public void reloadBundle() {
        tableLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_TABLE_NUMBER));
        gratuityLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_GRATUITY));
        printCheckButton.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_CHECK_PRINT));
        totalLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_TOTAL));
        assignmentPanel.reloadBundle();
    }

    public void setDetail(boolean detail) {
        gratuityField.setEnabled(!detail);
        gratuityLabel.setEnabled(!detail);
        tableNumberField.setEnabled(!detail);
        tableLabel.setEnabled(!detail);
        totalLabel.setEnabled(!detail);
        assignmentPanel.setDetail(detail);
    }

    public void setOrder(Order order) {
        selectedOrder = order;
        assignmentPanel.setOrder(order);
        gratuityField.setText(Double.toString(order.getGratuity()));
        tableNumberField.setText(Integer.toString(order.getTableNumber()));
        setTotal();
    }

    public void setUser(User user) {
        assignmentPanel.setUser(user);
    }

    public void updateFields() {
        selectedOrder.setGratuity(Double.parseDouble(gratuityField.getText()));
        selectedOrder.setTableNumber(Integer.parseInt(tableNumberField.getText()));
    }

    private void setTotal() {
        if(selectedOrder != null) {
            totalField.setText(Double.toString(FoodAssignmentDao.getFoodAssignmentByOrderId(selectedOrder.getId()).stream().mapToDouble(assign -> FoodDao.getFoodById(assign.getFoodId()).getPrice()).sum() + selectedOrder.getGratuity()));
        }
    }


}
