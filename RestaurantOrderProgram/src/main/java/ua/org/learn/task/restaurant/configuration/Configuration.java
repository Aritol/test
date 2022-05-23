package ua.org.learn.task.restaurant.configuration;

import ua.org.learn.task.restaurant.constant.StringConstant;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Configuration {
    private static Configuration instance = null;

    private Properties commonProperties;
    private Properties imageProperties;
    private ResourceBundle resourceBundle;

    private Configuration() {
        load();
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getCommonProperty(String name) {
        return commonProperties.getProperty(name);
    }

    public String getImageProperty(String name) {
        return imageProperties.getProperty(name);
    }

    public String getBundleProperty(String name) {
        return resourceBundle.getString(name);
    }

    public void loadLanguagePack(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(StringConstant.PATH_LANGUAGE, locale);
    }

    private void load() {
        commonProperties = new Properties();
        imageProperties = new Properties();

        File commonConfig = new File(ClassLoader.getSystemClassLoader().getResource(StringConstant.PATH_CONFIGURATION_COMMON).getPath());
        if (commonConfig.exists()) {
            try(Reader reader = new FileReader(commonConfig)) {
                commonProperties.load(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        File imageConfig = new File(ClassLoader.getSystemClassLoader().getResource(StringConstant.PATH_CONFIGURATION_IMAGE).getPath());
        if (imageConfig.exists()) {
            try(Reader reader = new FileReader(imageConfig)) {
                imageProperties.load(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        loadLanguagePack(Locale.getDefault());
    }
}
