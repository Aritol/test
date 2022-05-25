package ua.org.learn.task.restaurant.ui.order;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.FoodAssignmentDao;
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

    private final AssignmentPanel assignmentPanel;

    public OrderDetailPanel() {
        super();

        assignmentPanel = new AssignmentPanel();
        add(assignmentPanel, BorderLayout.CENTER);

//        JPanel userActionPanel = new JPanel();
//        add(userActionPanel, BorderLayout.SOUTH);
//
//        removeFood = UiComponentUtil.createButton(
//                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_DETAIL),
//                event -> {
////                    FoodAssignment selectedAssignment = assignmentTableModel.getFoodAssignmentByRow(assignmentTable.getSelectedRow());
////                    if (selectedAssignment != null) {
////                        FoodAssignmentDao.removeFoodAssignmentById(selectedAssignment.getId());
////                        assignmentTableModel.fireTableDataChanged();
////                    }
//                }
//        );
//        userActionPanel.add(removeFood);
//
//        changeState = UiComponentUtil.createButton(
//                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_CREATE),
//                event -> {
////                    OrderDao.createOrder(createNewOrder());
////                    orderTableModel.fireTableDataChanged();
////                    Order selectedOrder = OrderDao.getLastOrder();
////                    if (selectedOrder != null) {
////                        modifyOrder(selectedOrder, ModifyType.EDIT);
////                    }
//                }
//        );
//        userActionPanel.add(changeState);
    }

    public void modify() {
        assignmentPanel.modify();
    }

    public void reloadBundle() {

        assignmentPanel.reloadBundle();
    }

    public void setOrder(Order order) {
        assignmentPanel.setOrder(order);
    }

    public void setUser(User user) {
    }
}
