package core.utils;

import core.config.project.ProjectProperties;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyReader {

    final static Logger logger = Logger.getLogger(PropertyReader.class);
    private static Properties properties = null;
    private static URL propertyFileUrl;
    private static String propertyFileName;

    private static URL getUrl(String propertyFileName) {
        return ProjectProperties.class.getClassLoader().getResource(propertyFileName);
    }

    public static void showListOfProperties(java.util.Properties properties) {
        for (String key : properties.stringPropertyNames()) {
            String propertyInfo = String.format("Property: %s = %s", key, properties.getProperty(key));
            System.out.println(propertyInfo);
        }
    }

    public static boolean areThePropertiesEmpty() {
        if (properties == null) {
            propertyFileUrl = getUrl(propertyFileName);
            return true;
        }
        return false;
    }

    public static boolean isPropertyFileUrl() {
        if (propertyFileUrl == null) {
            logger.error("Property files '" + propertyFileName + "' dosn't exists");
            return false;
        }
        return true;
    }

    public static void loadListOfProperties() {
        try {
            InputStream inputStream = new FileInputStream(propertyFileUrl.getPath());
            properties = new Properties();
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            showListOfProperties(properties);
        } catch (final IOException e) {
            logger.error("Exception while with reading application properties file: " + e);
        }
    }

    public static Properties getListOfProperties(String expectedPropertyFileName) {
        propertyFileName = expectedPropertyFileName;
        if (areThePropertiesEmpty() && isPropertyFileUrl()) {
            loadListOfProperties();
        }
        return properties;
    }
}
