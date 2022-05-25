package ua.org.learn.task.restaurant.ui.food.assignment;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.FoodAssignmentDao;
import ua.org.learn.task.restaurant.model.FoodAssignment;
import ua.org.learn.task.restaurant.model.Order;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.order.OrderModifyForm;
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
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_FOOD_REMOVE),
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
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_CHANGE_STATE),
                event -> {
                    FoodAssignment selectedAssignment = assignmentTableModel.getFoodAssignmentByRow(assignmentTable.getSelectedRow());
                    if (selectedAssignment != null) {
                        AssignmentModifyForm.getInstance().setCurrentUser(currentUser);
                        AssignmentModifyForm.getInstance().setFoodAssignment(selectedAssignment);
                        AssignmentModifyForm.getInstance().setVisible(true);
                        OrderModifyForm.getInstance().setVisible(false);
                    }
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
        AssignmentModifyForm.getInstance().reloadBundle();
    }

    public void setDetail(boolean isDetail) {
        changeState.setEnabled(!isDetail);
        removeFood.setEnabled(!isDetail);
    }

    public void setOrder(Order order) {
        assignmentTableModel.setOrder(order);
    }

    public void setUser(User user) {
        currentUser = user;
    }
}
