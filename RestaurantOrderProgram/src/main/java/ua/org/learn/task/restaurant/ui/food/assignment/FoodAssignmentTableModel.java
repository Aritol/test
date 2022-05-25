package ua.org.learn.task.restaurant.ui.food.assignment;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.FoodAssignmentDao;
import ua.org.learn.task.restaurant.dao.FoodDao;
import ua.org.learn.task.restaurant.dao.OrderDao;
import ua.org.learn.task.restaurant.model.Food;
import ua.org.learn.task.restaurant.model.FoodAssignment;
import ua.org.learn.task.restaurant.model.Order;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class FoodAssignmentTableModel extends AbstractTableModel {
    private Order selectedOrder;
    private List<FoodAssignment> foodAssignments;

    public FoodAssignmentTableModel() {
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return foodAssignments != null ? foodAssignments.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> State.class;
            case 1 -> String.class;
            case 2 -> Double.class;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_FOOD_STATE);
            case 1 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_FOOD_NAME);
            case 2 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_FOOD_PRICE);
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Food food = FoodDao.getFoodById(foodAssignments.get(rowIndex).getFoodId());
        return switch (columnIndex) {
            case 0 -> foodAssignments.get(rowIndex).getState();
            case 1 -> food.getName();
            case 2 -> food.getPrice();
            default -> null;
        };
    }

    @Override
    public void fireTableDataChanged() {
        if (selectedOrder != null) {
            foodAssignments = FoodAssignmentDao.getFoodAssignmentByOrderId(selectedOrder.getId());
        }
        super.fireTableDataChanged();
    }

    public FoodAssignment getFoodAssignmentByRow(int row) {
        if (row >= 0 && row < foodAssignments.size()) {
            return foodAssignments.get(row);
        } else {
            return null;
        }
    }

    public void setOrder(Order order) {
        this.selectedOrder = order;
        fireTableDataChanged();
    }
}
