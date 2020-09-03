package core.factories;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static core.config.AppProperties.*;

public class CapabilitiesFactory {

    public static final int SYSTEM_PORT = getSystemPort();
    public static final String PLATFORM_NAME = getPlatformName();
    public static final String PLATFORM_VERSION = getPlatformVersion();
    public static final String DEVICE = getDevice();
    public static final String AUTOMATION = getAutomationName();
    public static final String LANGUAGE = getDefaultLanguage();
    public static final String LOCALE = getDefaultLocale();

    public static DesiredCapabilities getTruckerCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("platformVersion", PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE);
        capabilities.setCapability("appPackage", APP_PACKAGE);
        capabilities.setCapability("appActivity", APP_ACTIVITY);
        capabilities.setCapability("automationName", AUTOMATION);
        capabilities.setCapability("systemPort", SYSTEM_PORT);
        capabilities.setCapability("udid", DEVICE);
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("newCommandTimeout", 3000);
        capabilities.setCapability("appWaitDuration", 200000L);
        capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 35000);
        capabilities.setCapability("noSign", true);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("language", LANGUAGE);
        capabilities.setCapability("locale", LOCALE);
        return capabilities;
    }
}
