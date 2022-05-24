package ua.org.learn.task.restaurant.ui.food;

import ua.org.learn.task.restaurant.configuration.Configuration;
import ua.org.learn.task.restaurant.constant.ModifyType;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.dao.FoodDao;
import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.model.Food;
import ua.org.learn.task.restaurant.model.User;
import ua.org.learn.task.restaurant.ui.main.MainForm;
import ua.org.learn.task.restaurant.ui.user.UserModifyForm;
import ua.org.learn.task.restaurant.ui.user.UserTableModel;
import ua.org.learn.task.restaurant.ui.util.UiComponentUtil;

import javax.swing.*;

public class FoodPanel extends JPanel {
    private User currentUser;

    private final JButton addFood;
    private final JButton editFood;
    private final JButton removeFood;
    private final JTable foodTable;
    private final FoodTableModel foodTableModel;

    public FoodPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        foodTableModel = new FoodTableModel();
        foodTable = UiComponentUtil.createTable(foodTableModel);
        add(new JScrollPane(foodTable));

        JPanel foodActionPanel = new JPanel();
        foodActionPanel.setLayout(new BoxLayout(foodActionPanel, BoxLayout.LINE_AXIS));
        add(foodActionPanel);

        addFood = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_FOOD_ADD),
                event -> {
                    FoodModifyForm.getInstance().setModifyType(ModifyType.CREATE);
                    FoodModifyForm.getInstance().setModifiedFood(null);
                    FoodModifyForm.getInstance().setCurrentUser(currentUser);
                    FoodModifyForm.getInstance().setVisible(true);
                    MainForm.getInstance().setVisible(false);
                }
        );
        foodActionPanel.add(addFood);

        editFood = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_FOOD_EDIT),
                event -> {
                    Food selectedFood = foodTableModel.getFoodByRow(foodTable.getSelectedRow());
                    if (selectedFood != null) {
                        FoodModifyForm.getInstance().setModifyType(ModifyType.EDIT);
                        FoodModifyForm.getInstance().setModifiedFood(selectedFood);
                        FoodModifyForm.getInstance().setCurrentUser(currentUser);
                        FoodModifyForm.getInstance().setVisible(true);
                        MainForm.getInstance().setVisible(false);
                    }
                }
        );
        foodActionPanel.add(editFood);

        removeFood = UiComponentUtil.createButton(
                Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_FOOD_REMOVE),
                event -> {
                    Food selectedFood = foodTableModel.getFoodByRow(foodTable.getSelectedRow());
                    if (selectedFood != null) {
                        FoodDao.removeFoodById(selectedFood.getId());
                        foodTableModel.fireTableDataChanged();
                    }
                }
        );
        foodActionPanel.add(removeFood);
    }

    public void setUser(User user) {
        currentUser = user;
    }

    public void modelUpdate() {
        foodTableModel.fireTableDataChanged();
    }

    public void reloadBundle() {
        addFood.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_FOOD_ADD));
        editFood.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_FOOD_EDIT));
        removeFood.setText(Configuration.getInstance().getBundleProperty(StringConstant.BUNDLE_LABEL_BUTTON_FOOD_REMOVE));
        foodTable.createDefaultColumnsFromModel();
    }
}
