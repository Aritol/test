package ua.org.learn.task.restaurant.ui.order;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.OrderDao;
import ua.org.learn.task.restaurant.model.Order;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {
    private boolean isHistory;
    private List<Order> orders;

    public OrderTableModel() {
        isHistory = false;
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return orders != null ? orders.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> State.class;
            case 1,4 -> String.class;
            case 2 -> Integer.class;
            case 3, 5  -> Date.class;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_ORDER_STATE);
            case 1 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_ORDER_EXECUTOR);
            case 2 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_COMMON_TABLE_NUMBER);
            case 3 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_ORDER_DATE_ON);
            case 4 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_COMMON_UPDATED_BY);
            case 5 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_COMMON_UPDATED_ON);
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> orders.get(rowIndex).getState();
            case 1 -> orders.get(rowIndex).getExecutor();
            case 2 -> orders.get(rowIndex).getTableNumber();
            case 3-> orders.get(rowIndex).getDateOn();
            case 4 -> orders.get(rowIndex).getUpdatedBy();
            case 5 -> orders.get(rowIndex).getUpdatedOn();
            default -> null;
        };
    }

    @Override
    public void fireTableDataChanged() {
        orders = isHistory ? OrderDao.getHistory() : OrderDao.getActiveOrders();
        super.fireTableDataChanged();
    }

    public Order getOrderByRow(int row) {
        if (row >= 0 && row < orders.size()) {
            return orders.get(row);
        } else {
            return null;
        }
    }

    public void setHistory(boolean isHistory) {
        this.isHistory = isHistory;
        fireTableDataChanged();
    }
}
