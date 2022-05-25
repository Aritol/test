package ua.org.learn.task.restaurant.dao;

import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    private final static String SELECT_ALL_FOODS = "SELECT * FROM FOOD";
    private final static String INSERT_FOOD = "INSERT INTO FOOD (DESCRIPTION, NAME, PRICE, UPDATED_BY, UPDATED_ON, WEIGHT) " +
            "VALUES(?,?,?,?,?,?)";
    private final static String UPDATE_FOOD = "UPDATE FOOD SET DESCRIPTION = ?, NAME = ?, PRICE = ?, UPDATED_BY = ?, UPDATED_ON=?, WEIGHT = ? " +
            "WHERE ID = ?";
    private final static String REMOVE_FOOD = "DELETE FROM FOOD WHERE ID =?";

    public static List<Food> getAllFoods() {
        List<Food> users = new ArrayList<>();
        try (Connection connection = DBUtil.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FOODS);
            while (resultSet.next()) {
                users.add(buildFood(resultSet));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static void createFood(Food food) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FOOD)) {
            setStatementParameters(statement, food);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Food getFoodById(long id) {
        return getAllFoods().stream().filter(food -> food.getId() == id).findFirst().orElse(null);
    }

    public static void removeFoodById(long id) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_FOOD)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateFood(Food food) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_FOOD)) {
            setStatementParameters(statement, food);
            statement.setLong(7, food.getId());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Food buildFood(ResultSet resultSet) throws SQLException {
        return Food.builder()
                .description(resultSet.getString(StringConstant.COLUMN_DESCRIPTION))
                .id(resultSet.getLong(StringConstant.COLUMN_ID))
                .name(resultSet.getString(StringConstant.COLUMN_NAME))
                .price(resultSet.getDouble(StringConstant.COLUMN_PRICE))
                .updatedBy(resultSet.getString(StringConstant.COLUMN_UPDATED_BY))
                .updatedOn(resultSet.getDate(StringConstant.COLUMN_UPDATED_ON))
                .weight(resultSet.getDouble(StringConstant.COLUMN_WEIGHT))
                .build();
    }

    private static void setStatementParameters(PreparedStatement statement, Food food) throws SQLException {
        statement.setString(1, food.getDescription());
        statement.setString(2, food.getName());
        statement.setDouble(3, food.getPrice());
        statement.setString(4, food.getUpdatedBy());
        statement.setDate(5, food.getUpdatedOn());
        statement.setDouble(6, food.getWeight());
    }
}
