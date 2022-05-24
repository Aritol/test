package ua.org.learn.task.restaurant.ui.user;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.constant.UserRole;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.time.Instant;

public class UserModifyForm extends JFrame {
    private static UserModifyForm instance = null;

    private User currentUser;
    private long modifyId;
    private ModifyType modifyType;

    private final JCheckBox activeCheckbox;
    private final JLabel loginLabel;
    private final JTextField loginField;
    private final JTextField nameField;
    private final JLabel nameLabel;
    private final JTextField passwordField;
    private final JLabel passwordLabel;
    private final JComboBox<UserRole> roleField;
    private final JLabel roleLabel;
    private final JButton saveButton;
    private final JTextField surnameField;
    private final JLabel surnameLabel;

    public UserModifyForm() {
        setSize(500, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        GridBagLayout mainPanel = new GridBagLayout();
        setLayout(mainPanel);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;

        nameLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_USER_NAME));
        nameField = UiComponentUtil.createTextField();
        UiComponentUtil.locateComponent(this, mainPanel, constraint, nameLabel, nameField);

        surnameLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_USER_SURNAME));
        surnameField = UiComponentUtil.createTextField();
        UiComponentUtil.locateComponent(this, mainPanel, constraint, surnameLabel, surnameField);

        loginLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_LOGIN));
        loginField = UiComponentUtil.createTextField();
        UiComponentUtil.locateComponent(this, mainPanel, constraint, loginLabel, loginField);

        passwordLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_PASSWORD));
        passwordField = UiComponentUtil.createTextField();
        UiComponentUtil.locateComponent(this, mainPanel, constraint, passwordLabel, passwordField);

        roleLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_ROLE));
        roleField = UiComponentUtil.createComboBox(UserRole.ADMINISTRATOR, UserRole.MANAGER);
        UiComponentUtil.locateComponent(this, mainPanel, constraint, roleLabel, roleField);

        activeCheckbox = UiComponentUtil.createCheckBox(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_ACTIVE));
        mainPanel.setConstraints(activeCheckbox, constraint);
        add(activeCheckbox);

        saveButton = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_SAVE),
                event -> {
                    if (validateFields()) {
                        switch (modifyType) {
                            case CREATE -> UserDao.createUser(getModifiedUser());
                            case EDIT -> UserDao.updateUser(getModifiedUser());
                        }
                        UserModifyForm.this.setVisible(false);
                        MainForm.getInstance().updateUserList();
                        MainForm.getInstance().setVisible(true);
                    }
                }
        );
        mainPanel.setConstraints(saveButton, constraint);
        add(saveButton);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                MainForm.getInstance().setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public static UserModifyForm getInstance() {
        if (instance == null) {
            instance = new UserModifyForm();
        }
        return instance;
    }

    public void reloadBundle() {
        setTitle(Configuration.getInstance().getBundleProperty(modifyType == ModifyType.CREATE
                ? StringConstant.BUNDLE_LABEL_FORM_USER_CREATE
                : StringConstant.BUNDLE_LABEL_FORM_USER_EDIT
        ));
        nameLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_USER_NAME));
        surnameLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_USER_SURNAME));
        loginLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_LOGIN));
        passwordLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_PASSWORD));
        roleLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_ROLE));
        activeCheckbox.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_ACTIVE));
        saveButton.setText(Configuration.getInstance().getBundleProperty(modifyType == ModifyType.CREATE
                ? StringConstant.BUNDLE_LABEL_BUTTON_CREATE
                : StringConstant.BUNDLE_LABEL_BUTTON_SAVE
        ));
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setModifyType(ModifyType type) {
        modifyType = type;
        switch (modifyType) {
            case CREATE -> loginField.setEnabled(Boolean.TRUE);
            case EDIT -> loginField.setEnabled(Boolean.FALSE);
        }
        reloadBundle();
    }

    public void setModifiedUser(User user) {
        if (user != null) {
            loginField.setText(user.getLogin());
            nameField.setText(user.getName());
            passwordField.setText(user.getPassword());
            surnameField.setText(user.getSurname());
            roleField.setSelectedItem(user.getRole());
            activeCheckbox.setSelected(user.getActive());
            modifyId = user.getId();
        } else {
            loginField.setText("");
            nameField.setText("");
            passwordField.setText("");
            surnameField.setText("");
            roleField.setSelectedItem(null);
            activeCheckbox.setSelected(Boolean.TRUE);
            modifyId = 0;
        }
    }

    private User getModifiedUser() {
        return User.builder()
                .id(modifyId)
                .isActive(activeCheckbox.isSelected())
                .login(loginField.getText())
                .name(nameField.getText())
                .password(passwordField.getText())
                .role((UserRole) roleField.getSelectedItem())
                .surname(surnameField.getText())
                .updatedBy(currentUser.getLogin())
                .updatedOn(new Date(Instant.now().toEpochMilli()))
                .build();
    }

    private boolean validateFields() {
        return UiComponentUtil.checkFieldValue(this, nameField.getText(), Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_USER_NAME))
                && UiComponentUtil.checkFieldValue(this, surnameField.getText(), Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_USER_SURNAME))
                && UiComponentUtil.checkFieldValue(this, loginField.getText(), Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_LOGIN))
                && UiComponentUtil.checkFieldValue(this, passwordField.getText(), Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_PASSWORD))
                && UiComponentUtil.checkFieldValue(this, roleField.getSelectedItem(), Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_ROLE));
    }
}
