package core.factories;

import core.adb.AdbCommandExecutor;
import core.config.project.ProjectProperties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

    final static Logger logger = Logger.getLogger(AppiumServerFactory.class);
    public static AndroidDriver<AndroidElement> driver;

    public DriverFactory(DesiredCapabilities desiredCapabilities) {
        connectToDevice(desiredCapabilities);
    }

    private void connectToDevice(DesiredCapabilities desiredCapabilities) {
        AdbCommandExecutor.adbConnect();
        driver = new AndroidDriver<>(AppiumServerFactory.getUrl(), desiredCapabilities);
        logger.info("Connected to device: " + ProjectProperties.getDevice());
    }
}