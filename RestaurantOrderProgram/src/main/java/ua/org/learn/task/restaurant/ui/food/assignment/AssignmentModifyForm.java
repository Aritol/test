package ua.org.learn.task.restaurant.ui.food.assignment;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UserRole;
import ua.org.learn.task.restaurant.dao.FoodAssignmentDao;
import ua.org.learn.task.restaurant.model.FoodAssignment;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.ui.order.OrderModifyForm;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.time.Instant;

public class AssignmentModifyForm extends JFrame {
    private static AssignmentModifyForm instance = null;

    private User currentUser;
    private FoodAssignment foodAssignment;

    private final JButton saveButton;
    private final JComboBox<State> stateField;
    private final JLabel stateLabel;


    public AssignmentModifyForm() {
        setSize(500, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        GridBagLayout mainPanel = new GridBagLayout();
        setLayout(mainPanel);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;

        stateLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_STATE));
        stateField = UiComponentUtil.createComboBox(State.NEW, State.IN_PROGRESS, State.READY, State.COMPLETE, State.CANCELLED);
        UiComponentUtil.locateComponent(this, mainPanel, constraint, stateLabel, stateField);

       saveButton = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_SAVE),
                event -> {
                    foodAssignment.setState((State) stateField.getSelectedItem());
                    FoodAssignmentDao.updateFoodAssignment(foodAssignment);
                    OrderModifyForm.getInstance().modify();
                    OrderModifyForm.getInstance().setVisible(true);
                    setVisible(false);
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
                OrderModifyForm.getInstance().setVisible(true);
                setVisible(false);
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

    public static AssignmentModifyForm getInstance() {
        if (instance == null) {
            instance = new AssignmentModifyForm();
        }
        return instance;
    }

    public void reloadBundle() {
        stateLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_STATE));
        saveButton.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_SAVE));
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void setFoodAssignment(FoodAssignment foodAssignment) {
        this.foodAssignment = foodAssignment;
        if (foodAssignment != null) {
            stateField.setSelectedItem(foodAssignment.getState());
        }
    }
}
