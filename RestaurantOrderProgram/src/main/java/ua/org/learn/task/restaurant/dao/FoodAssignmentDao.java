package ua.org.learn.task.restaurant.dao;

import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.model.FoodAssignment;
import ua.org.learn.task.restaurant.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodAssignmentDao {
    private final static String SELECT_ALL_FOOD_ASSIGNMENTS = "SELECT * FROM FOOD_ASSIGNMENT ORDER BY ID";
    private final static String INSERT_FOOD_ASSIGNMENT = "INSERT INTO FOOD_ASSIGNMENT (FOOD_ID, ORDER_ID, STATE, UPDATED_BY, UPDATED_ON) VALUES(?,?,?,?,?)";
    private final static String UPDATE_FOOD_ASSIGNMENT = "UPDATE FOOD_ASSIGNMENT SET STATE = ?, UPDATED_BY = ?, UPDATED_ON = ? WHERE ID = ?";
    private final static String REMOVE_FOOD_ASSIGNMENT = "DELETE FROM FOOD_ASSIGNMENT WHERE ID = ?";

    public static void createFoodAssignment(FoodAssignment foodAssignment) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FOOD_ASSIGNMENT)) {
            statement.setLong(1, foodAssignment.getFoodId());
            statement.setLong(2, foodAssignment.getOrderId());
            statement.setString(3, foodAssignment.getState().toString());
            statement.setString(4, foodAssignment.getUpdatedBy());
            statement.setDate(5, foodAssignment.getUpdatedOn());
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<FoodAssignment> getAllOrders() {
        List<FoodAssignment> foodAssignments = new ArrayList<>();
        try (Connection connection = DBUtil.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FOOD_ASSIGNMENTS);
            while (resultSet.next()) {
                foodAssignments.add(buildFoodAssignment(resultSet));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return foodAssignments;
    }

    public static List<FoodAssignment> getFoodAssignmentByOrderId(long orderId) {
        return getAllOrders().stream().filter(assignment -> assignment.getOrderId() == orderId).toList();
    }

    public static void removeFoodAssignmentById(long id) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(REMOVE_FOOD_ASSIGNMENT)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateFoodAssignment(FoodAssignment foodAssignment) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_FOOD_ASSIGNMENT)) {
            statement.setString(1, foodAssignment.getState().toString());
            statement.setString(2, foodAssignment.getUpdatedBy());
            statement.setDate(3, foodAssignment.getUpdatedOn());
            statement.setLong(4, foodAssignment.getId());
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static FoodAssignment buildFoodAssignment(ResultSet resultSet) throws SQLException {
        return FoodAssignment.builder()
                .foodId(resultSet.getLong(StringConstant.COLUMN_FOOD_ID))
                .id(resultSet.getLong(StringConstant.COLUMN_ID))
                .orderId(resultSet.getLong(StringConstant.COLUMN_ORDER_ID))
                .state(State.getStateByName(resultSet.getString(StringConstant.COLUMN_STATE)))
                .updatedBy(resultSet.getString(StringConstant.COLUMN_UPDATED_BY))
                .updatedOn(resultSet.getDate(StringConstant.COLUMN_UPDATED_ON))
                .build();
    }
}
