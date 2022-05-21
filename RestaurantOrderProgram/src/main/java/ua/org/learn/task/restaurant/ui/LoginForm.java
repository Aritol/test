package ua.org.learn.task.restaurant.ui;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.exception.BusinessException;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.service.UserService;
import ua.org.learn.task.restaurant.ui.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginForm extends JFrame {
    private static LoginForm instance = null;

    private Configuration configuration;

    private JButton loginButton;
    private JTextField loginField;
    private JLabel loginLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;

    private LoginForm() {
        super();

        configuration = Configuration.getInstance();

        String iconPath = configuration.getImageProperty(StringConstant.PROPERTY_PATH_ICON_LOGIN_FORM);
        if (iconPath != null) {
            Image formIcon = ImageUtil.getImage(iconPath);
            if (formIcon != null) {
                setIconImage(formIcon);
            }
        }
        setSize(300, 120);
        setResizable(false);
        setTitle("Login to Restaurant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout mainPanel = new GridBagLayout();
        setLayout(mainPanel);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weightx = 1;

        loginLabel = new JLabel("Login");
        mainPanel.setConstraints(loginLabel, constraint);
        add(loginLabel);

        loginField = new JTextField();
        constraint.weightx = 3;
        constraint.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.setConstraints(loginField, constraint);
        add(loginField);

        passwordLabel = new JLabel("Password");
        constraint.weightx = 1;
        constraint.gridwidth = GridBagConstraints.RELATIVE;
        mainPanel.setConstraints(passwordLabel, constraint);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setMinimumSize(new Dimension(200,40));
        constraint.weightx = 3;
        constraint.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.setConstraints(passwordField, constraint);
        add(passwordField);

        loginButton = new JButton("Sign-in");
        loginButton.addActionListener(event -> {
            try {
                User user = UserService.getInstance().getUserByLoginPassword(loginField.getText(), new String(passwordField.getPassword()));
                MainForm.getInstance().setUser(user);
                MainForm.getInstance().setVisible(true);
                LoginForm.this.setVisible(false);
            } catch (BusinessException e) {
                JOptionPane.showMessageDialog(LoginForm.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        mainPanel.setConstraints(loginButton, constraint);
        add(loginButton);
    }

    public void applyBundle() {

    }

    public static LoginForm getInstance() {
        if (instance == null) {
            instance = new LoginForm();
        }
        return instance;
    }
}
