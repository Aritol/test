package ua.org.learn.task.restaurant.constant;

public interface StringConstant {
    String BUNDLE_LABEL_BUTTON_CREATE = "label.button.create";
    String BUNDLE_LABEL_BUTTON_SAVE = "label.button.save";
    String BUNDLE_LABEL_BUTTON_SIGN_IN = "label.button.sign.in";
    String BUNDLE_LABEL_BUTTON_USER_ADD = "label.button.user.add";
    String BUNDLE_LABEL_BUTTON_USER_EDIT = "label.button.user.edit";
    String BUNDLE_LABEL_BUTTON_USER_REMOVE = "label.button.user.remove";
    String BUNDLE_LABEL_COLUMN_COMMON_UPDATED_BY = "label.column.common.updated.by";
    String BUNDLE_LABEL_COLUMN_COMMON_UPDATED_ON = "label.column.common.updated.on";
    String BUNDLE_LABEL_COLUMN_ORDER_ACTIVE = "label.column.user.active";
    String BUNDLE_LABEL_COLUMN_USER_ACTIVE = "label.column.user.active";
    String BUNDLE_LABEL_COLUMN_USER_LOGIN = "label.column.user.login";
    String BUNDLE_LABEL_COLUMN_USER_NAME = "label.column.user.name";
    String BUNDLE_LABEL_COLUMN_USER_PASSWORD = "label.column.user.password";
    String BUNDLE_LABEL_COLUMN_USER_ROLE = "label.column.user.role";
    String BUNDLE_LABEL_COLUMN_USER_SURNAME = "label.column.user.surname";
    String BUNDLE_LABEL_ERROR = "label.error";
    String BUNDLE_LABEL_FIELD_ACTIVE = "label.field.active";
    String BUNDLE_LABEL_FIELD_LOGIN = "label.field.login";
    String BUNDLE_LABEL_FIELD_LANGUAGE = "label.field.language";
    String BUNDLE_LABEL_FIELD_PASSWORD = "label.field.password";
    String BUNDLE_LABEL_FIELD_ROLE = "label.field.role";
    String BUNDLE_LABEL_FIELD_USER_NAME = "label.field.user.name";
    String BUNDLE_LABEL_FIELD_USER_SURNAME = "label.field.user.surname";
    String BUNDLE_LABEL_FORM_LOGIN = "label.form.login";
    String BUNDLE_LABEL_FORM_MAIN = "label.form.main";
    String BUNDLE_LABEL_FORM_USER_CREATE= "label.form.user.create";
    String BUNDLE_LABEL_FORM_USER_EDIT = "label.form.user.edit";
    String BUNDLE_LABEL_MENU_FOOD = "label.menu.food";
    String BUNDLE_LABEL_MENU_FOOD_LIST = "label.menu.food.list";
    String BUNDLE_LABEL_MENU_ORDER = "label.menu.order";
    String BUNDLE_LABEL_MENU_ORDER_HISTORY = "label.menu.order.history";
    String BUNDLE_LABEL_MENU_ORDER_LIST = "label.menu.order.list";
    String BUNDLE_LABEL_MENU_USER = "label.menu.user";
    String BUNDLE_LABEL_MENU_USER_EXIT = "label.menu.user.exit";
    String BUNDLE_LABEL_MENU_USER_LIST = "label.menu.user.list";
    String BUNDLE_LABEL_PANEL_STATUS_LOGGED = "label.panel.status.logged";
    String BUNDLE_LABEL_WARNING = "label.warning";
    String BUNDLE_MESSAGE_FIELD_CANNOT_BE_EMPTY = "message.field.cannot.be.empty";
    String BUNDLE_MESSAGE_USER_LOGIN_WRONG = "message.user.login.wrong";

    String COLUMN_CUSTOMER = "CUSTOMER";
    String COLUMN_DATE_ON = "DATE_ON";
    String COLUMN_DESCRIPTION = "DESCRIPTION";
    String COLUMN_GRATUITY = "GRATUITY";
    String COLUMN_ID = "ID";
    String COLUMN_IS_ACTIVE = "IS_ACTIVE";
    String COLUMN_LOGIN = "LOGIN";
    String COLUMN_NAME = "NAME";
    String COLUMN_PASSWORD = "PASSWORD";
    String COLUMN_PICTURE = "PICTURE";
    String COLUMN_PRICE = "PRICE";
    String COLUMN_ROLE = "ROLE";
    String COLUMN_STATE = "STATE";
    String COLUMN_SURNAME = "SURNAME";
    String COLUMN_TABLE_NUMBER = "TABLE_NUMBER";
    String COLUMN_UPDATED_BY = "UPDATED_BY";
    String COLUMN_UPDATED_ON = "UPDATED_ON";
    String COLUMN_WEIGHT = "WEIGHT";


    
    String DEFAULT_BUNDLE = "restaurant";

    String MESSAGE_CANNOT_LOAD_RESOURCES = "Cannot load inner resource(s)";

    String PATH_CONFIGURATION_COMMON = "config/common.properties";
    String PATH_CONFIGURATION_IMAGE = "config/image.properties";
    String PATH_LANGUAGE = "lang/lang";

    String PROPERTY_DB_DRIVER = "db.driver";
    String PROPERTY_DB_PASSWORD = "db.password";
    String PROPERTY_DB_URL = "db.url";
    String PROPERTY_DB_USER = "db.user";
    String PROPERTY_PATH_ICON_LOGIN = "path.icon.login";
    String PROPERTY_PATH_ICON_LOGIN_FORM = "path.icon.login.form";
    String PROPERTY_PATH_MAIN_ICON = "path.icon.main";

    String TO_STRING_FOOD_ASSIGNMENT = "FOOD_ID=%s;ORDER_ID=%s;STATE=%s;UPDATED_BY=%s;UPDATED_ON=%s";
    String TO_STRING_FOOD = "ID=%s;NAME=%s;WEIGHT=%s;PRICE=%s;DESCRIPTION=%s;UPDATED_BY=%s;UPDATED_ON=%s";
    String TO_STRING_ORDER = "ID=%s;CUSTOMER=%s;DATE_ON=%s;TABLE_NUMBER=%s;EXECUTOR=%s;STATE=%s;GRATUITY=%s;UPDATED_BY=%s;UPDATED_ON=%s";
    String TO_STRING_USER = "ID=%s;NAME=%s;SURNAME=%s;LOGIN=%s;PASSWORD=%s;ROLE=%s;UPDATED_BY=%s;UPDATED_ON=%s;IS_ACTIVE=%s";
}
