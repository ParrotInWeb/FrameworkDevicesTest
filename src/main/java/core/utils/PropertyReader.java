package core.utils;

import core.config.project.ProjectProperties;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyReader {

    final static Logger logger = Logger.getLogger(PropertyReader.class);
    private static java.util.Properties properties = null;

    private static URL getUrl(String propertyFileName) {
        return ProjectProperties.class.getClassLoader().getResource(propertyFileName);
    }

    public static void showListOfProperties(java.util.Properties properties) {
        for (String key : properties.stringPropertyNames()) {
            System.out.println("Property: " + key + " = " + properties.getProperty(key));
        }
    }

    public static Properties listOfProperties(String propertyFileName) {
        if (properties == null) {
            URL propertyFileUrl = getUrl(propertyFileName);

            if (propertyFileUrl == null) {
                logger.error("Property files '" + propertyFileName + "' dosn't exists");
            } else {
                try {
                    InputStream inputStream = new FileInputStream(propertyFileUrl.getPath());
                    properties = new Properties();
                    properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    showListOfProperties(properties);
                } catch (final IOException e) {
                    logger.error("Exception while with reading application properties file: " + e);
                }
            }
        }
        return properties;
    }
}
