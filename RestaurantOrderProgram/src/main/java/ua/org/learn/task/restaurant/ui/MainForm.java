package ua.org.learn.task.restaurant.ui;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.exception.BusinessException;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.service.UserService;
import ua.org.learn.task.restaurant.ui.table.UserTableModel;
import ua.org.learn.task.restaurant.ui.util.ImageUtil;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MainForm extends JFrame {
    private static MainForm instance = null;

    private Configuration configuration;
    private User user;

    private JPanel foodPanel;
    private JLabel loggedLabel;
    private JPanel orderPanel;
    private JTabbedPane tabbedPane;
    private JPanel userPanel = new JPanel();

    private MainForm() {
        super();

        configuration = Configuration.getInstance();

        String iconPath = configuration.getImageProperty(StringConstant.PROPERTY_PATH_MAIN_ICON);
        if (iconPath != null) {
            Image formIcon = ImageUtil.getImage(iconPath);
            if (formIcon != null) {
                setIconImage(formIcon);
            }
        }
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setTitle("Restaurant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        orderPanel = new JPanel();

        userPanel = new JPanel();
        JPanel userActionPanel = new JPanel();
        userPanel.add(userActionPanel, BorderLayout.NORTH);
        try {
            JTable userTable = new JTable(new UserTableModel());
            userTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            TableCellRenderer cellRenderer = userTable.getDefaultRenderer(JButton.class);
            userTable.setDefaultRenderer(JButton.class, new JComponentTableCellRender(cellRenderer));
            userPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);
        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }

        foodPanel = new JPanel();

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        loggedLabel = new JLabel();
        loggedLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(loggedLabel);

    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            loggedLabel.setText(String.format("Logged as %s %s", user.getName(), user.getSurname()));

            cleanTab();
            switch (user.getRole()) {
                case ADMINISTRATOR:
                    setAdministratorVisibility();
                    break;
                case CUSTOMER:
                    setCustomerVisibility();
                    break;
                case MANAGER:
                    setManagerVisibility();
                    break;
            }
        }
    }

    private void setAdministratorVisibility() {
        tabbedPane.addTab("Order", orderPanel);
        tabbedPane.addTab("Food", foodPanel);
        tabbedPane.addTab("User", userPanel);
    }

    private void setCustomerVisibility() {
        tabbedPane.addTab("Order", orderPanel);
        tabbedPane.addTab("Food", foodPanel);
    }

    private void setManagerVisibility() {
        tabbedPane.addTab("Order", orderPanel);
        tabbedPane.addTab("Food", foodPanel);
    }

    private void cleanTab() {
        while (tabbedPane.getTabCount() > 0) {
            tabbedPane.removeTabAt(0);
        }
    }

    public static MainForm getInstance() {
        if (instance == null) {
            instance = new MainForm();
        }
        return instance;
    }
}
