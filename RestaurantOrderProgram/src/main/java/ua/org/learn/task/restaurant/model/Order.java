package ua.org.learn.task.restaurant.model;

import ua.org.learn.task.restaurant.constant.StringConstant;

import java.time.Instant;
import java.util.Date;

public class Order {
    private String customer;
    private Instant dateOn;
    private String executor;
    private double gratuity;
    private long id;
    private State state;
    private int tableNumber;
    private String updatedBy;
    private Date updatedOn;

    public String getCustomer() {
        return customer;
    }

    public Instant getDateOn() {
        return dateOn;
    }

    public long getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getExecutor() {
        return executor;
    }

    public State getState() {
        return state;
    }

    public double getGratuity() {
        return gratuity;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setDateOn(Instant dateOn) {
        this.dateOn = dateOn;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public void setGratuity(double gratuity) {
        this.gratuity = gratuity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    @Override
    public String toString() {
        return String.format(StringConstant.TO_STRING_ORDER, id, customer, dateOn, tableNumber, executor, state, gratuity, updatedBy, updatedOn);
    }

    public static class OrderBuilder {
        private Order order;

        public OrderBuilder() {
            order = new Order();
        }

        public OrderBuilder customer(String customer) {
            order.setCustomer(customer);
            return this;
        }

        public OrderBuilder dateOn(Instant dateOn) {
            order.setDateOn(dateOn);
            return this;
        }

        public OrderBuilder executor(String executor) {
            order.setExecutor(executor);
            return this;
        }

        public OrderBuilder gratuity(double gratuity) {
            order.setGratuity(gratuity);
            return this;
        }

        public OrderBuilder id(long id) {
            order.setId(id);
            return this;
        }

        public OrderBuilder state(State state) {
            order.setState(state);
            return this;
        }

        public OrderBuilder tableNumber(int tableNumber) {
            order.setTableNumber(tableNumber);
            return this;
        }

        public OrderBuilder updatedBy(String updatedBy) {
            order.setUpdatedBy(updatedBy);
            return this;
        }

        public OrderBuilder updatedOn(Date updatedOn) {
            order.setUpdatedOn(updatedOn);
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
