package ua.org.learn.task.restaurant.ui.food;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UserRole;
import ua.org.learn.task.restaurant.dao.FoodDao;
import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.model.Food;
import ua.org.learn.task.restaurant.model.User;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class FoodTableModel extends AbstractTableModel {
    private List<Food> foods;

    public FoodTableModel() {
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return foods != null ? foods.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 3 -> String.class;
            case 1, 2 -> Double.class;
            case 4 -> Date.class;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_FOOD_NAME);
            case 1 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_FOOD_WEIGHT);
            case 2 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_FOOD_PRICE);
            case 3 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_COMMON_UPDATED_BY);
            case 4 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_COMMON_UPDATED_ON);
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> foods.get(rowIndex).getName();
            case 1 -> foods.get(rowIndex).getWeight();
            case 2 -> foods.get(rowIndex).getPrice();
            case 3 -> foods.get(rowIndex).getUpdatedBy();
            case 4 -> foods.get(rowIndex).getUpdatedOn();
            default -> null;
        };
    }

    @Override
    public void fireTableDataChanged() {
        foods = FoodDao.getAllFoods();
        super.fireTableDataChanged();
    }

    public Food getFoodByRow(int row) {
        if (row >= 0 && row < foods.size()) {
            return foods.get(row);
        } else {
            return null;
        }
    }
}
