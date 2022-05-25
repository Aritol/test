package ua.org.learn.task.restaurant.ui.food.assignment;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.FoodAssignmentDao;
import ua.org.learn.task.restaurant.model.FoodAssignment;
import ua.org.learn.task.restaurant.model.Order;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;

public class AssignmentPanel extends JPanel {
    private User currentUser;
;
    private final JButton changeState;
    private final JButton removeFood;
    private final JTable assignmentTable;
    private final FoodAssignmentTableModel assignmentTableModel;

    public AssignmentPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        assignmentTableModel = new FoodAssignmentTableModel();
        assignmentTable = UiComponentUtil.createTable(assignmentTableModel);
        add(new JScrollPane(assignmentTable));

        JPanel userActionPanel = new JPanel();
        userActionPanel.setLayout(new BoxLayout(userActionPanel, BoxLayout.LINE_AXIS));
        add(userActionPanel);

        removeFood = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_DETAIL),
                event -> {
                    FoodAssignment selectedAssignment = assignmentTableModel.getFoodAssignmentByRow(assignmentTable.getSelectedRow());
                    if (selectedAssignment != null) {
                        FoodAssignmentDao.removeFoodAssignmentById(selectedAssignment.getId());
                        assignmentTableModel.fireTableDataChanged();
                    }
                }
        );
        userActionPanel.add(removeFood);

        changeState = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_CREATE),
                event -> {
//                    OrderDao.createOrder(createNewOrder());
//                    orderTableModel.fireTableDataChanged();
//                    Order selectedOrder = OrderDao.getLastOrder();
//                    if (selectedOrder != null) {
//                        modifyOrder(selectedOrder, ModifyType.EDIT);
//                    }
                }
        );
        userActionPanel.add(changeState);
    }

    public void modify() {
        assignmentTableModel.fireTableDataChanged();
    }

    public void reloadBundle() {
        changeState.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_CREATE));
        removeFood.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_ORDER_DETAIL));
        assignmentTable.createDefaultColumnsFromModel();
    }

    public void setOrder(Order order) {
        assignmentTableModel.setOrder(order);
    }

    public void setUser(User user) {
        currentUser = user;
    }
}
