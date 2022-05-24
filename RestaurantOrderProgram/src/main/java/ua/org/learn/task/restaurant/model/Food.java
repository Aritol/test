package ua.org.learn.task.restaurant.model;

import ua.org.learn.task.restaurant.constant.StringConstant;

import java.sql.Blob;
import java.sql.Date;
import java.time.Instant;

public class Food {
    private String description;
    private long id;
    private String name;
    private double price;
    private String updatedBy;
    private Date updatedOn;
    private double weight;

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public double getWeight() {
        return weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static FoodBuilder builder() {
        return new FoodBuilder();
    }

    @Override
    public String toString() {
        return String.format(StringConstant.TO_STRING_FOOD, id, name, weight, price, description, updatedBy, updatedOn);
    }

    public static class FoodBuilder {
        private final Food food;

        public FoodBuilder() {
            food = new Food();
        }

        public FoodBuilder description(String description) {
            food.setDescription(description);
            return this;
        }

        public FoodBuilder id(Long id) {
            food.setId(id);
            return this;
        }

        public FoodBuilder name(String name) {
            food.setName(name);
            return this;
        }

        public FoodBuilder price(double price) {
            food.setPrice(price);
            return this;
        }

        public FoodBuilder updatedBy(String updatedBy) {
            food.setUpdatedBy(updatedBy);
            return this;
        }

        public FoodBuilder updatedOn(Date updatedOn) {
            food.setUpdatedOn(updatedOn);
            return this;
        }

        public FoodBuilder weight(double weight) {
            food.setWeight(weight);
            return this;
        }

        public Food build() {
            return food;
        }
    }
}
