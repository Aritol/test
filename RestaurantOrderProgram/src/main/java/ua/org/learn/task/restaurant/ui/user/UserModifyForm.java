package ua.org.learn.task.restaurant.ui.user;

import ua.org.learn.task.restaurant.exception.BusinessException;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.model.UserRole;
import ua.org.learn.task.restaurant.service.UserService;
import ua.org.learn.task.restaurant.ui.form.MainForm;
import ua.org.learn.task.restaurant.ui.ModifyType;

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

    private JLabel loginLabel;
    private JTextField loginField;
    private JTextField nameField;
    private JLabel nameLabel;
    private JTextField passwordField;
    private JLabel passwordLabel;
    private JComboBox<UserRole> roleField;
    private JLabel roleLabel;
    private JButton saveButton;
    private JTextField surnameField;
    private JLabel surnameLabel;
    private JCheckBox activeCheckbox;

    public UserModifyForm() {
        setSize(300, 200);
        setResizable(false);
        setTitle("Login to Restaurant");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        GridBagLayout mainPanel = new GridBagLayout();
        setLayout(mainPanel);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;

        nameLabel = new JLabel("Name");
        nameField = new JTextField();
        addRow(mainPanel, constraint, nameLabel, nameField);

        surnameLabel = new JLabel("Surname");
        surnameField = new JTextField();
        addRow(mainPanel, constraint, surnameLabel, surnameField);

        loginLabel = new JLabel("Login");
        loginField = new JTextField();
        addRow(mainPanel, constraint, loginLabel, loginField);

        passwordLabel = new JLabel("Password");
        passwordField = new JTextField();
        addRow(mainPanel, constraint, passwordLabel, passwordField);

        roleLabel = new JLabel("Role");
        roleField = new JComboBox<>();
        roleField.addItem(UserRole.ADMINISTRATOR);
        roleField.addItem(UserRole.CUSTOMER);
        roleField.addItem(UserRole.MANAGER);
        addRow(mainPanel, constraint, roleLabel, roleField);

        activeCheckbox = new JCheckBox("Is Active User");
        mainPanel.setConstraints(activeCheckbox, constraint);
        add(activeCheckbox);

        saveButton = new JButton("Save");
        saveButton.addActionListener(event -> {
            try {
                switch (modifyType) {
                    case ADD:
                        UserService.getInstance().addUser(getModifiedUser());
                        break;
                    case EDIT:
                        UserService.getInstance().updateUser(getModifiedUser());
                        break;
                }
                UserModifyForm.this.setVisible(false);
                MainForm.getInstance().updateUserList();
                MainForm.getInstance().setVisible(true);
            } catch (BusinessException e) {
                JOptionPane.showMessageDialog(UserModifyForm.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
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

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setModifyType(ModifyType type) {
        modifyType = type;
        switch (modifyType) {
            case ADD:
                setTitle("New User");
                loginField.setEnabled(Boolean.TRUE);
                break;
            case EDIT:
                setTitle("Edit user");
                loginField.setEnabled(Boolean.FALSE);
                break;
        }
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

    private void addRow(GridBagLayout layout, GridBagConstraints constraint, Component label, Component field) {
        constraint.weightx = 1;
        constraint.gridwidth = GridBagConstraints.RELATIVE;
        layout.setConstraints(label, constraint);
        add(label);

        constraint.weightx = 3;
        constraint.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(field, constraint);
        add(field);
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
}
