package ua.org.learn.task.restaurant.ui.food;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.constant.UserRole;
import ua.org.learn.task.restaurant.dao.FoodDao;
import ua.org.learn.task.restaurant.model.Food;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.time.Instant;

public class FoodModifyForm extends JFrame {
    private static FoodModifyForm instance = null;

    private User currentUser;
    private long modifyId;
    private ModifyType modifyType;

    private final JTextField nameField;
    private final JLabel nameLabel;
    private final JSpinner priceField;
    private final JLabel priceLabel;
    private final JButton saveButton;
    private final JSpinner weightField;
    private final JLabel weightLabel;

    public FoodModifyForm() {
        setSize(500, 160);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        GridBagLayout mainPanel = new GridBagLayout();
        setLayout(mainPanel);

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;

        nameLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_FOOD_NAME));
        nameField = UiComponentUtil.createTextField();
        UiComponentUtil.locateComponent(this, mainPanel, constraint, nameLabel, nameField);

        weightLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_FOOD_WEIGHT));
        weightField = UiComponentUtil.createSpinner();
        UiComponentUtil.locateComponent(this, mainPanel, constraint, weightLabel, weightField);

        priceLabel = UiComponentUtil.createLabel(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_FOOD_PRICE));
        priceField = UiComponentUtil.createSpinner();
        UiComponentUtil.locateComponent(this, mainPanel, constraint, priceLabel, priceField);

        saveButton = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_SAVE),
                event -> {
                    if (validateFields()) {
                        switch (modifyType) {
                            case CREATE -> FoodDao.createFood(getModifiedFood());
                            case EDIT -> FoodDao.updateFood(getModifiedFood());
                        }
                        FoodModifyForm.this.setVisible(false);
                        MainForm.getInstance().updateFoodList();
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

    public static FoodModifyForm getInstance() {
        if (instance == null) {
            instance = new FoodModifyForm();
        }
        return instance;
    }

    public void reloadBundle() {
        setTitle(Configuration.getInstance().getBundleProperty(modifyType == ModifyType.CREATE
                ? StringConstant.BUNDLE_LABEL_FORM_FOOD_CREATE
                : StringConstant.BUNDLE_LABEL_FORM_FOOD_EDIT
        ));
        nameLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_FOOD_NAME));
        priceLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_FOOD_PRICE));
        weightLabel.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_FOOD_WEIGHT));
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
        reloadBundle();
    }

    public void setModifiedFood(Food food) {
        if (food != null) {
            nameField.setText(food.getName());
            priceField.setValue(food.getPrice());
            weightField.setValue(food.getWeight());
            modifyId = food.getId();
        } else {
            nameField.setText(null);
            priceField.setValue(0.0);
            weightField.setValue(0.0);
            modifyId = 0;
        }
    }

    private Food getModifiedFood() {
        return Food.builder()
                .id(modifyId)
                .name(nameField.getText())
                .price(Double.parseDouble(priceField.getValue().toString()))
                .updatedBy(currentUser.getLogin())
                .updatedOn(new Date(Instant.now().toEpochMilli()))
                .weight(Double.parseDouble(weightField.getValue().toString()))
                .build();
    }

    private boolean validateFields() {
        return UiComponentUtil.checkFieldValue(this, nameField.getText(), Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_FOOD_NAME))
                && UiComponentUtil.checkFieldValue(this, weightField.getValue(), Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_FOOD_WEIGHT))
                && UiComponentUtil.checkFieldValue(this, priceField.getValue(), Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_FIELD_FOOD_PRICE));
    }
}
