package ua.org.learn.task.restaurant.ui.order;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UserRole;
import ua.org.learn.task.restaurant.model.Order;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.service.OrderService;
import ua.org.learn.task.restaurant.service.UserService;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {
    private List<Order> orders;

    public OrderTableModel() {
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return orders != null ? orders.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 1, 2 -> String.class;
            case 3 -> Integer.class;
            case 4 -> State.class;
            case 5 -> Double.class;
            case 6, 7  -> Date.class;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
//            case 0 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_CUSTOMER);
//            case 1 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_EXECUTOR);
//            case 2 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_COMMON_UPDATED_BY);
//            case 3 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_TABLE_NUMBER);
//            case 4 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_STATE);
//            case 5 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_GRATUITY);
//            case 7 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_DATE_ON);
//            case 7 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_COMMON_UPDATED_ON);
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> orders.get(rowIndex).getCustomer();
            case 1 -> orders.get(rowIndex).getExecutor();
            case 2 -> orders.get(rowIndex).getUpdatedBy();
            case 3-> orders.get(rowIndex).getTableNumber();
            case 4 -> orders.get(rowIndex).getState();
            case 5 -> orders.get(rowIndex).getGratuity();
            case 6 -> orders.get(rowIndex).getDateOn();
            case 7 -> orders.get(rowIndex).getUpdatedOn();
            default -> null;
        };
    }

    @Override
    public void fireTableDataChanged() {
        orders = OrderService.getInstance().getAllUsers();
        super.fireTableDataChanged();
    }

    public Order getUserByRow(int row) {
        if (row >= 0 && row < orders.size()) {
            return orders.get(row);
        } else {
            return null;
        }
    }
}
