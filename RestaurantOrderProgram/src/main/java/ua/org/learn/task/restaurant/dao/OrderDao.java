package ua.org.learn.task.restaurant.dao;

import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;
import ua.org.learn.task.restaurant.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private final static String SELECT_ALL_ORDERS = "SELECT * FROM 'ORDER' ORDER BY DATE_ON DESC";
    private final static String INSERT_ORDER = "INSERT INTO 'ORDER' (CUSTOMER, DATE_ON, EXECUTOR, GRATUITY, STATE, TABLE_NUMBER, UPDATED_BY, " +
            "UPDATED_ON) VALUES(?,?,?,?,?,?,?,?)";
    private final static String UPDATE_ORDER = "UPDATE 'ORDER' SET CUSTOMER = ?, DATE_ON = ?, EXECUTOR = ?, GRATUITY = ?, STATE = ?, " +
            "TABLE_NUMBER = ?, UPDATED_BY = ?, UPDATED_ON = ? WHERE ID = ?";

    public static void createOrder(Order order) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ORDER)) {
            setStatementParameters(statement, order);
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DBUtil.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_ORDERS);
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static List<Order> getActiveOrders() {
        return getAllOrders().stream().filter(order -> order.getState() != State.COMPLETE && order.getState() != State.CANCELLED).toList();
    }

    public static List<Order> getHistory() {
        return getAllOrders().stream().filter(order -> order.getState() == State.COMPLETE || order.getState() == State.CANCELLED).toList();
    }

    public static Order getLastOrder() {
        return getAllOrders().stream().max((order1, order2) -> (int)(order1.getId() - order2.getId())).orElse(null);
    }

    public static Order getOrderById(long id) throws ClassNotFoundException, SQLException {
        return getAllOrders().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public static void updateOrder(Order order) {
        try (Connection connection = DBUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER)) {
            setStatementParameters(statement, order);
            statement.setLong(9, order.getId());
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static Order buildOrder(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .id(resultSet.getLong(StringConstant.COLUMN_ID))
                .customer(resultSet.getString(StringConstant.COLUMN_CUSTOMER))
                .dateOn(resultSet.getDate(StringConstant.COLUMN_DATE_ON))
                .executor(resultSet.getString(StringConstant.COLUMN_EXECUTOR))
                .gratuity(resultSet.getDouble(StringConstant.COLUMN_GRATUITY))
                .state(State.getStateByName(resultSet.getString(StringConstant.COLUMN_STATE)))
                .tableNumber(resultSet.getInt(StringConstant.COLUMN_TABLE_NUMBER))
                .updatedBy(resultSet.getString(StringConstant.COLUMN_UPDATED_BY))
                .updatedOn(resultSet.getDate(StringConstant.COLUMN_UPDATED_ON))
                .build();
    }

    private static void setStatementParameters(PreparedStatement statement, Order order) throws SQLException {
        statement.setString(1, order.getCustomer());
        statement.setDate(2, order.getDateOn());
        statement.setString(3, order.getExecutor());
        statement.setDouble(4, order.getGratuity());
        statement.setString(5, order.getState().toString());
        statement.setInt(6, order.getTableNumber());
        statement.setString(7, order.getUpdatedBy());
        statement.setDate(8, order.getUpdatedOn());
    }
}
