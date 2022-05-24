package ua.org.learn.task.restaurant.ui.user;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.constant.UserRole;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private List<User> users;

    public UserTableModel() {
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return users != null ? users.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 1, 2, 3, 4 -> String.class;
            case 5 -> UserRole.class;
            case 6 -> Date.class;
            case 7 -> Boolean.class;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_USER_NAME);
            case 1 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_USER_SURNAME);
            case 2 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_USER_LOGIN);
            case 3 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_USER_PASSWORD);
            case 4 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_COMMON_UPDATED_BY);
            case 5 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_USER_ROLE);
            case 6 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_COMMON_UPDATED_ON);
            case 7 -> Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_COLUMN_USER_ACTIVE);
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> users.get(rowIndex).getName();
            case 1 -> users.get(rowIndex).getSurname();
            case 2 -> users.get(rowIndex).getLogin();
            case 3 -> users.get(rowIndex).getPassword();
            case 4 -> users.get(rowIndex).getUpdatedBy();
            case 5 -> users.get(rowIndex).getRole();
            case 6 -> users.get(rowIndex).getUpdatedOn();
            case 7 -> users.get(rowIndex).getActive();
            default -> null;
        };
    }

    @Override
    public void fireTableDataChanged() {
        users = UserDao.getAllUsers();
        super.fireTableDataChanged();
    }

    public User getUserByRow(int row) {
        if (row >= 0 && row < users.size()) {
            return users.get(row);
        } else {
            return null;
        }
    }
}
