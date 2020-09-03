package core.config;

import java.util.Properties;

import static core.utils.PropertyReader.getProperties;

public class AppProperties {

    public static final boolean STATE_OF_NOTIFICATIONS = true;
    public static final boolean STATE_OF_WI_FI_CONNECTION = true;
    public static final boolean STATE_OF_DATA_CONNECTION = false;
    public static final String APP_PACKAGE = "com.finalwire.aida64";
    public static final String APP_ACTIVITY = "HHMainActivity";

    private static Properties getAppProperties() {
        return getProperties("application.properties");
    }

    public static String getNodeJsPath() {
        return getAppProperties().getProperty("appium.nodeJsPath");
    }

    public static String getAppiumPath() {
        return getAppProperties().getProperty("appium.appiumJsPath");
    }

    public static String getBootstrapPort() {
        return getAppProperties().getProperty("bootstrap.port");
    }

    public static Integer getSystemPort() {
        return Integer.valueOf(getAppProperties().getProperty("system.port"));
    }

    public static String getPlatformName() {
        return getAppProperties().getProperty("platform.Name");
    }

    public static String getPlatformVersion() {
        return getAppProperties().getProperty("platform.Version");
    }

    public static String getDefaultLanguage() {
        return getAppProperties().getProperty("platform.defaultLanguage");
    }

    public static String getDefaultLocale() {
        return getAppProperties().getProperty("platform.defaultLocale");
    }

    public static String getDevice() {
        return getAppProperties().getProperty("device.serial");
    }

    public static String getAutomationName() {
        return getAppProperties().getProperty("appium.AutomationName");
    }
}
