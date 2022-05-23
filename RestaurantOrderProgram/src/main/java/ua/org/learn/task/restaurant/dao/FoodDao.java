package ua.org.learn.task.restaurant.dao;

import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    private final static String SELECT_ALL_FOODS = "SELECT * FROM FOOD";
    private final static String INSERT_FOOD = "INSERT INTO FOOD (DESCRIPTION, NAME, PICTURE, PRICE, UPDATED_BY, UPDATED_ON, WEIGHT) " +
            "VALUES(?,?,?,?,?,?,?)";
    private final static String UPDATE_FOOD = "UPDATE FOOD SET DESCRIPTION = ?, NAME = ?, PICTURE = ?, PRICE = ?, UPDATED_BY = ?, UPDATED_ON, " +
            "WEIGHT = ? = ? WHERE ID = ?";
    private final static String REMOVE_FOOD = "DELETE FROM FOOD WHERE ID =?";
    public static List<Food> getAllFoods() throws ClassNotFoundException, SQLException {
        List<Food> users = new ArrayList<>();
        try (Connection connection = DBUtil.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FOODS);
            while (resultSet.next()) {
                users.add(buildFood(resultSet));
            }
        }
        return users;
    }

    public static Food createFood(Food food) throws ClassNotFoundException, SQLException {
        int changeLines = 0;
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FOOD)) {
            statement.setString(1, food.getDescription());
            statement.setString(2, food.getName());
            statement.setBlob(3, food.getPicture());
            statement.setDouble(4, food.getPrice());
            statement.setString(5, food.getUpdatedBy());
            statement.setDate(6, food.getUpdatedOn());
            statement.setDouble(7, food.getWeight());
            changeLines = statement.executeUpdate();
        }
        return changeLines > 0 ? getFoodByName(food.getName()) : null;
    }

    public static Food getFoodById(long id) throws ClassNotFoundException, SQLException {
        return getAllFoods().stream().filter(food -> food.getId() == id).findFirst().orElse(null);
    }

    public static Food getFoodByName(String name) throws ClassNotFoundException, SQLException {
        return getAllFoods().stream().filter(food -> food.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static void removeFoodById(long id) throws ClassNotFoundException, SQLException {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_FOOD)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public static void updateFood(Food food) throws ClassNotFoundException, SQLException {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_FOOD)) {
            statement.setString(1, food.getDescription());
            statement.setString(2, food.getName());
            statement.setBlob(3, food.getPicture());
            statement.setDouble(4, food.getPrice());
            statement.setString(5, food.getUpdatedBy());
            statement.setDate(6, food.getUpdatedOn());
            statement.setDouble(7, food.getWeight());
            statement.setLong(8, food.getId());
            statement.executeUpdate();
        }
    }

    private static Food buildFood(ResultSet resultSet) throws SQLException {
        return Food.builder()
                .description(resultSet.getString(StringConstant.COLUMN_DESCRIPTION))
                .id(resultSet.getLong(StringConstant.COLUMN_ID))
                .name(resultSet.getString(StringConstant.COLUMN_NAME))
                .picture(resultSet.getBlob(StringConstant.COLUMN_PICTURE))
                .price(resultSet.getDouble(StringConstant.COLUMN_PRICE))
                .updatedBy(resultSet.getString(StringConstant.COLUMN_UPDATED_BY))
                .updatedOn(resultSet.getDate(StringConstant.COLUMN_UPDATED_ON))
                .weight(resultSet.getDouble(StringConstant.COLUMN_WEIGHT))
                .build();
    }
}
