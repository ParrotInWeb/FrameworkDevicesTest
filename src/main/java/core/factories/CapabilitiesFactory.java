package core.factories;

import core.config.AppConfig;
import core.config.project.ProjectProperties;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesFactory {

    public static DesiredCapabilities getTruckerCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", ProjectProperties.getPlatformName());
        capabilities.setCapability("platformVersion", ProjectProperties.getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ProjectProperties.getDevice());
        capabilities.setCapability("appPackage", AppConfig.APP_PACKAGE);
        capabilities.setCapability("appActivity", AppConfig.APP_ACTIVITY);
        capabilities.setCapability("automationName", ProjectProperties.getAutomationName());
        capabilities.setCapability("systemPort", ProjectProperties.getSystemPort());
        capabilities.setCapability("udid", ProjectProperties.getDevice());
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("newCommandTimeout", 3000);
        capabilities.setCapability("appWaitDuration", 200000L);
        capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 35000);
        capabilities.setCapability("noSign", true);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("language", ProjectProperties.getDefaultLanguage());
        capabilities.setCapability("locale", ProjectProperties.getDefaultLocale());
        return capabilities;
    }
}
