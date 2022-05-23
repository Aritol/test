package ua.org.learn.task.restaurant.ui.user;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.service.UserService;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;

public class UserPanel extends JPanel {
    private User currentUser;

    private final JButton addUser;
    private final JButton editUser;
    private final JButton removeUser;
    private final JTable userTable;
    private final  UserTableModel userTableModel;

    public UserPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        userTableModel = new UserTableModel();
        userTable = UiComponentUtil.createTable(userTableModel);
        add(new JScrollPane(userTable));

        JPanel userActionPanel = new JPanel();
        userActionPanel.setLayout(new BoxLayout(userActionPanel, BoxLayout.LINE_AXIS));
        add(userActionPanel);

        addUser = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_USER_ADD),
                event -> {
                    UserModifyForm.getInstance().setModifyType(ModifyType.ADD);
                    UserModifyForm.getInstance().setCurrentUser(currentUser);
                    UserModifyForm.getInstance().setVisible(true);
                    MainForm.getInstance().setVisible(false);
                }
        );
        userActionPanel.add(addUser);

        editUser = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_USER_EDIT),
                event -> {
                    User selectedUser = userTableModel.getUserByRow(userTable.getSelectedRow());
                    if (selectedUser != null) {
                        UserModifyForm.getInstance().setModifyType(ModifyType.EDIT);
                        UserModifyForm.getInstance().setModifiedUser(selectedUser);
                        UserModifyForm.getInstance().setCurrentUser(currentUser);
                        UserModifyForm.getInstance().setVisible(true);
                        MainForm.getInstance().setVisible(false);
                    }
                }
        );
        userActionPanel.add(editUser);

        removeUser = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_USER_REMOVE),
                event -> {
                    User selectedUser = userTableModel.getUserByRow(userTable.getSelectedRow());
                    if (selectedUser != null) {
                        UserService.getInstance().removeUserById(selectedUser.getId());
                        userTableModel.fireTableDataChanged();
                    }
                }
        );
        userActionPanel.add(removeUser);
    }

    public void setUser(User user) {
        currentUser = user;
    }

    public void modelUpdate() {
        userTableModel.fireTableDataChanged();
    }

    public void reloadBundle() {
        addUser.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_USER_ADD));
        editUser.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_USER_EDIT));
        removeUser.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_USER_REMOVE));
    }
}
