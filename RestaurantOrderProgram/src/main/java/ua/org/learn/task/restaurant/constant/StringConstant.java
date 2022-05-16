package ua.org.learn.task.restaurant.constant;

public interface StringConstant {
    String COLUMN_ID = "ID";
    String COLUMN_IS_ACTIVE = "IS_ACTIVE";
    String COLUMN_LOGIN = "LOGIN";
    String COLUMN_NAME = "NAME";
    String COLUMN_PASSWORD = "PASSWORD";
    String COLUMN_ROLE = "ROLE";
    String COLUMN_SURNAME = "SURNAME";
    String COLUMN_UPDATED_BY = "UPDATED_BY";
    String COLUMN_UPDATED_ON = "UPDATED_ON";
    
    
    String DEFAULT_BUNDLE = "restaurant";

    String TO_STRING_FOOD_ASSIGNMENT = "FOOD_ID=%s;ORDER_ID=%s;STATE=%s;UPDATED_BY=%s;UPDATED_ON=%s";
    String TO_STRING_FOOD = "ID=%s;NAME=%s;WEIGHT=%s;PRICE=%s;DESCRIPTION=%s;UPDATED_BY=%s;UPDATED_ON=%s";
    String TO_STRING_ORDER = "ID=%s;CUSTOMER=%s;DATE_ON=%s;TABLE_NUMBER=%s;EXECUTOR=%s;STATE=%s;GRATUITY=%s;UPDATED_BY=%s;UPDATED_ON=%s";
    String TO_STRING_USER = "ID=%s;NAME=%s;SURNAME=%s;LOGIN=%s;PASSWORD=%s;ROLE=%s;UPDATED_BY=%s;UPDATED_ON=%s;IS_ACTIVE=%s";
}
