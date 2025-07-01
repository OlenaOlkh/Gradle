package com.at.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private static final String PROPERTIES_FILE_PATH = "properties/common.properties";

    private static PropertyLoader instance;
    private final Properties properties;

    private PropertyLoader() {
        properties = new Properties();
        loadProperties();
    }

    public static synchronized PropertyLoader getInstance() {
        if (instance == null) {
            instance = new PropertyLoader();
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("Property file was not found by path '" + PROPERTIES_FILE_PATH + "'");
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load property file: " + PROPERTIES_FILE_PATH, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
