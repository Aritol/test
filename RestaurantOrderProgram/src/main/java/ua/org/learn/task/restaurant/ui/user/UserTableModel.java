package ua.org.learn.task.restaurant.ui.user;

import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.exception.BusinessException;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.model.UserRole;
import ua.org.learn.task.restaurant.service.UserService;

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
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return String.class;
            case 5:
                return UserRole.class;
            case 6:
                return Date.class;
            case 7:
                return Boolean.class;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return StringConstant.COLUMN_NAME;
            case 1:
                return StringConstant.COLUMN_SURNAME;
            case 2:
                return StringConstant.COLUMN_LOGIN;
            case 3:
                return StringConstant.COLUMN_PASSWORD;
            case 4:
                return StringConstant.COLUMN_UPDATED_BY;
            case 5:
                return StringConstant.COLUMN_ROLE;
            case 6:
                return  StringConstant.COLUMN_UPDATED_ON;
            case 7:
                return StringConstant.COLUMN_IS_ACTIVE;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return users.get(rowIndex).getName();
            case 1:
                return users.get(rowIndex).getSurname();
            case 2:
                return users.get(rowIndex).getLogin();
            case 3:
                return users.get(rowIndex).getPassword();
            case 4:
                return users.get(rowIndex).getUpdatedBy();
            case 5:
                return users.get(rowIndex).getRole();
            case 6:
                return users.get(rowIndex).getUpdatedOn();
            case 7:
                return users.get(rowIndex).getActive();
            default:
                return null;
        }
    }

    @Override
    public void fireTableDataChanged() {
        try {
            users = UserService.getInstance().getAllUsers();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
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
