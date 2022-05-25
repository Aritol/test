package ua.org.learn.task.restaurant.model;

import ua.org.learn.task.restaurant.constant.State;
import ua.org.learn.task.restaurant.constant.StringConstant;

import java.sql.Date;
import java.time.Instant;

public class FoodAssignment {
    private long foodId;
    private long id;
    private long orderId;
    private State state;
    private String updatedBy;
    private Date updatedOn;

    public long getFoodId() {
        return foodId;
    }

    public long getId() {
        return id;
    }

    public long getOrderId() {
        return orderId;
    }

    public State getState() {
        return state;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public static FoodAssignmentBuilder builder() {
        return new FoodAssignmentBuilder();
    }

    @Override
    public String toString() {
        return String.format(StringConstant.TO_STRING_FOOD_ASSIGNMENT, foodId, orderId, state, updatedBy, updatedOn);
    }

    public static class FoodAssignmentBuilder {
        private FoodAssignment foodAssignment;

        public FoodAssignmentBuilder() {
            foodAssignment = new FoodAssignment();
        }

        public FoodAssignmentBuilder foodId(long foodId) {
            foodAssignment.setFoodId(foodId);
            return this;
        }

        public FoodAssignmentBuilder id(long id) {
            foodAssignment.setId(id);
            return this;
        }

        public FoodAssignmentBuilder orderId(long orderId) {
            foodAssignment.setOrderId(orderId);
            return this;
        }

        public FoodAssignmentBuilder state(State state) {
            foodAssignment.setState(state);
            return this;
        }

        public FoodAssignmentBuilder updatedBy(String updatedBy) {
            foodAssignment.setUpdatedBy(updatedBy);
            return this;
        }

        public FoodAssignmentBuilder updatedOn(Date updatedOn) {
            foodAssignment.setUpdatedOn(updatedOn);
            return this;
        }

        public FoodAssignment build() {
            return foodAssignment;
        }
    }
}
