package core.config.project;

import java.util.Properties;

import static core.utils.PropertyReader.getListOfProperties;

public class ProjectProperties {

    public static boolean clearNotification = true;
    public static boolean changeWiFiConnection = true;
    public static boolean changeDataConnection = true;

    private static Properties appProperties() {
        return getListOfProperties("application.properties");
    }

    public static String getNodeJsPath() {
        return appProperties().getProperty("appium.nodeJsPath");
    }

    public static String getAppiumPath() {
        return appProperties().getProperty("appium.appiumJsPath");
    }

    public static String getBootstrapPort() { return appProperties().getProperty("bootstrap.port"); }

    public static Integer getSystemPort() {
        return Integer.valueOf(appProperties().getProperty("system.port"));
    }

    public static String getPlatformName() {
        return appProperties().getProperty("platform.Name");
    }

    public static String getPlatformVersion() {
        return appProperties().getProperty("platform.Version");
    }

    public static String getDefaultLanguage() {
        return appProperties().getProperty("platform.defaultLanguage");
    }

    public static String getDefaultLocale() {
        return appProperties().getProperty("platform.defaultLocale");
    }

    public static String getDevice() { return appProperties().getProperty("device.serial"); }

    public static String getAutomationName() { return appProperties().getProperty("appium.AutomationName"); }
}
