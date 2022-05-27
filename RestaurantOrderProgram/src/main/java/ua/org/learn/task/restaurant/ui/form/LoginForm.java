package ua.org.learn.task.restaurant.ui.form;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UiConstant;
import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.ui.util.ImageUtil;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class LoginForm extends JFrame {
    private static LoginForm instance = null;

    private final JLabel languageLabel;
    private final JButton loginButton;
    private final JTextField loginField;
    private final JLabel loginLabel;
    private final JPasswordField passwordField;
    private final JLabel passwordLabel;

    private LoginForm() {
        super();

        String iconPath = Configuration.getInstance().getImageProperty(StringConstant.PROPERTY_PATH_ICON_LOGIN_FORM);
        if (iconPath != null) {
            Image formIcon = ImageUtil.getImage(iconPath);
            if (formIcon != null) {
                setIconImage(formIcon);
            }
        }
        setFont(UiConstant.FONT_ITALIC_16);
        setSize(500, 160);
        setResizable(false);
        setTitle(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FORM_LOGIN));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout mainPanel = new GridBagLayout();
        setLayout(mainPanel);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;



        loginLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_LOGIN));
        loginField = UiComponentUtil.createTextField();
        UiComponentUtil.locateComponent(this, mainPanel, constraint, loginLabel, loginField);

        passwordLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_PASSWORD));
        passwordField = UiComponentUtil.createPasswordField();
        UiComponentUtil.locateComponent(this, mainPanel, constraint, passwordLabel, passwordField);

        languageLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_LANGUAGE));
        JComboBox<String> languageComboBox = UiComponentUtil.createComboBox("uk", "en");
        languageComboBox.setSelectedItem("en");
        languageComboBox.addItemListener(event -> {
            Configuration.getInstance().loadLanguagePack(new Locale(event.getItem().toString()));
            reloadBundle();
        });
        UiComponentUtil.locateComponent(this, mainPanel, constraint, languageLabel, languageComboBox);

        loginButton = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_SIGN_IN),
                event -> {
                    User user = UserDao.getUserByLogin(loginField.getText());
                    if (user != null && user.getActive() && user.getPassword().equals(new String(passwordField.getPassword()))) {
                        MainForm.getInstance().setUser(user);
                        MainForm.getInstance().setVisible(true);
                        LoginForm.this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_MESSAGE_USER_LOGIN_WRONG),
                                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_ERROR),
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
        );
        mainPanel.setConstraints(loginButton, constraint);
        add(loginButton);
    }
    public static LoginForm getInstance() {
        if (instance == null) {
            instance = new LoginForm();
        }
        return instance;
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            clearForm();
        }
        super.setVisible(b);
    }

    public void reloadBundle() {
        setTitle(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FORM_LOGIN));
        loginLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_LOGIN));
        passwordLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_PASSWORD));
        languageLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_LANGUAGE));
        loginButton.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_SIGN_IN));
        MainForm.getInstance().reloadBundle();
    }

    private void clearForm() {
        loginField.setText(null);
        passwordField.setText(null);
    }
}
