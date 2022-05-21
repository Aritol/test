package ua.org.learn.task.restaurant.ui.table;

import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.exception.BusinessException;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.model.UserRole;
import ua.org.learn.task.restaurant.service.UserService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private List<User> users;

    public UserTableModel() throws BusinessException {
        updateUserList();
    }
    @Override
    public int getRowCount() {
        return users.size();
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
                return String.class;
            case 4:
                return UserRole.class;
            case 5:
                return Boolean.class;
            case 6:
            case 7:
                return JButton.class;
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
                return StringConstant.COLUMN_ROLE;
            case 5:
                return StringConstant.COLUMN_IS_ACTIVE;
            case 6:
            case 7:
                return "";
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
                return users.get(rowIndex).getRole();
            case 5:
                return users.get(rowIndex).getActive();
            case 6:
                return getEditButton(users.get(rowIndex).getId());
            case 7:
                return getRemoveButton(users.get(rowIndex).getId());
            default:
                return null;
        }
    }

    public void updateUserList() throws BusinessException {
        users = UserService.getInstance().getAllUsers();
    }

    private JButton getEditButton(long id) {
        JButton edit = new JButton("Edit");
        edit.addActionListener(e -> {
        });
        return edit;
    }

    private JButton getRemoveButton(long id) {
        JButton edit = new JButton("Remove");
        edit.addActionListener(event -> {
            try {
                UserService.getInstance().removeUserById(id);
                updateUserList();
                fireTableDataChanged();
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        });
        return edit;
    }
}
