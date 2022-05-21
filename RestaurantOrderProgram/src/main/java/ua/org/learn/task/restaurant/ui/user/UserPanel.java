package ua.org.learn.task.restaurant.ui.user;

import ua.org.learn.task.restaurant.exception.BusinessException;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.service.UserService;
import ua.org.learn.task.restaurant.ui.form.MainForm;
import ua.org.learn.task.restaurant.ui.ModifyType;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    private User currentUser;
    private JButton addUser;
    private JButton editUser;
    private JButton removeUser;
    private JTable userTable;
    private UserTableModel userTableModel;
    private User selectedUser;

    public UserPanel() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel userActionPanel = new JPanel();
        userActionPanel.setPreferredSize(new Dimension(500,40));
        userActionPanel.setMaximumSize(userActionPanel.getPreferredSize());
        add(userActionPanel);

        addUser = new JButton("Add");
        addUser.addActionListener(event -> {
            UserModifyForm.getInstance().setModifyType(ModifyType.ADD);
            UserModifyForm.getInstance().setCurrentUser(currentUser);
            UserModifyForm.getInstance().setModifiedUser(null);
            UserModifyForm.getInstance().setVisible(true);
            MainForm.getInstance().updateUserList();
            MainForm.getInstance().setVisible(false);
        });
        userActionPanel.add(addUser);

        editUser = new JButton("Edit");
        editUser.addActionListener(event -> {
            User selectedUser = userTableModel.getUserByRow(userTable.getSelectedRow());
            if (selectedUser != null) {
                UserModifyForm.getInstance().setModifyType(ModifyType.EDIT);
                UserModifyForm.getInstance().setCurrentUser(currentUser);
                UserModifyForm.getInstance().setModifiedUser(selectedUser);
                UserModifyForm.getInstance().setVisible(true);
            }
            MainForm.getInstance().setVisible(false);
        });
        userActionPanel.add(editUser);

        removeUser = new JButton("Remove");
        removeUser.addActionListener(event -> {
            User selectedUser = userTableModel.getUserByRow(userTable.getSelectedRow());
            if (selectedUser != null) {
                try {
                    UserService.getInstance().removeUserById(selectedUser.getId());
                    userTableModel.fireTableDataChanged();
                } catch (BusinessException e) {
                    JOptionPane.showMessageDialog(UserPanel.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        userActionPanel.add(removeUser);
        userTableModel = new UserTableModel();

        userTable = new JTable(userTableModel);
        userTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        add(new JScrollPane(userTable));
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void modelUpdate() {
        userTableModel.fireTableDataChanged();
    }
}
