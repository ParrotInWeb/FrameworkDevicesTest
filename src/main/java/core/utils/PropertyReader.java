package core.utils;

import core.config.AppProperties;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyReader {

    private final static Logger logger = Logger.getLogger(PropertyReader.class);
    private static Properties properties = null;
    private static URL propertyFileUrl;
    private static String propertyFileName;

    public static Properties getProperties(String expectedPropertyFileName) {
        propertyFileName = expectedPropertyFileName;
        if (areThePropertiesEmpty() && isPropertyFileUrl()) {
            loadProperties();
        }
        return properties;
    }

    private static boolean areThePropertiesEmpty() {
        if (properties == null) {
            propertyFileUrl = getUrl(propertyFileName);
            return true;
        }
        return false;
    }

    private static URL getUrl(String propertyFileName) {
        return AppProperties.class.getClassLoader().getResource(propertyFileName);
    }

    private static boolean isPropertyFileUrl() {
        if (propertyFileUrl == null) {
            logger.error("Property files '" + propertyFileName + "' dosn't exists");
            return false;
        }
        return true;
    }

    private static void loadProperties() {
        try {
            InputStream inputStream = new FileInputStream(propertyFileUrl.getPath());
            properties = new Properties();
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            showProperties(properties);
        } catch (final IOException e) {
            logger.error("Exception while with reading application properties file: " + e);
        }
    }

    private static void showProperties(java.util.Properties properties) {
        System.out.println("List of property:");
        for (String key : properties.stringPropertyNames()) {
            String propertyInfo = String.format("Property: %s = %s", key, properties.getProperty(key));
            System.out.println(propertyInfo);
        }
    }
}
