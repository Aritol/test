package ua.org.learn.task.restaurant.ui.food.assignment;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.FoodAssignmentDao;
import ua.org.learn.task.restaurant.dao.OrderDao;
import ua.org.learn.task.restaurant.model.Food;
import ua.org.learn.task.restaurant.model.FoodAssignment;
import ua.org.learn.task.restaurant.model.Order;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.food.FoodTableModel;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.ui.order.OrderModifyForm;
import ua.org.learn.task.restaurant.ui.order.OrderTableModel;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;
import java.sql.Date;
import java.time.Instant;

public class FoodAssignmentPanel extends JPanel {
    private Order currentOrder;
    private User currentUser;

    private final JButton assignFood;
    private final JTable foodTable;
    private final FoodTableModel foodTableModel;

    public FoodAssignmentPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        foodTableModel = new FoodTableModel();
        foodTable = UiComponentUtil.createTable(foodTableModel);
        add(new JScrollPane(foodTable));

        JPanel userActionPanel = new JPanel();
        userActionPanel.setLayout(new BoxLayout(userActionPanel, BoxLayout.LINE_AXIS));
        add(userActionPanel);

        assignFood = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_FOOD_ASSIGN),
                event -> {
                    Food selectedFood = foodTableModel.getFoodByRow(foodTable.getSelectedRow());
                    if (selectedFood != null) {
                        FoodAssignmentDao.createFoodAssignment(createNewFoodAssignment(selectedFood));
                        OrderModifyForm.getInstance().modify();
                        JOptionPane.showMessageDialog(
                                this,
                                String.format(
                                        Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_MESSAGE_FOOD_ASSIGN),
                                        selectedFood.getName(),
                                        currentOrder.getId()
                                ),
                                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_INFO),
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
        );
        userActionPanel.add(assignFood);
    }

    public void modify() {
        foodTableModel.fireTableDataChanged();
    }

    public void reloadBundle() {
        assignFood.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_FOOD_ASSIGN));
        foodTable.createDefaultColumnsFromModel();
    }

    public void setOrder(Order order) {
        currentOrder = order;
    }

    public void setUser(User user) {
        currentUser = user;
    }

    private FoodAssignment createNewFoodAssignment(Food food) {
        return FoodAssignment.builder()
                .foodId(food.getId())
                .orderId(currentOrder.getId())
                .state(State.IN_PROGRESS)
                .updatedBy(currentUser.getLogin())
                .updatedOn(new Date(Instant.now().toEpochMilli()))
                .build();
    }
}
