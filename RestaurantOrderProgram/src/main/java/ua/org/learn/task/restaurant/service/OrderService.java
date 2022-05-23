package ua.org.learn.task.restaurant.service;

import ua.org.learn.task.restaurant.dao.OrderDao;
import ua.org.learn.task.restaurant.dao.UserDao;
import ua.org.learn.task.restaurant.model.Order;
import ua.org.learn.task.restaurant.model.User;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private static OrderService instance = null;

    private OrderService() {
        super();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public void addOrder(Order order) {
        OrderDao.createOrder(order);
    }

    public List<Order> getAllUsers() {
       return OrderDao.getAllOrders();
    }

    public void removeUserById(long id)  {
        OrderDao.removeOrderById(id);
    }

    public void updateUser(Order order) {
        OrderDao.updateOrder(order);
    }
}
