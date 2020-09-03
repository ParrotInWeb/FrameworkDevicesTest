package test.base;

import core.config.AppProperties;
import core.factories.AppiumServerFactory;
import core.factories.CapabilitiesFactory;
import core.factories.DriverFactory;
import core.adb.AdbCmdExecutor;
import core.config.OcularConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.testng.annotations.*;

import static core.factories.ClassesWithDriverFactory.*;

public abstract class AppiumBaseTest {

    protected AndroidDriver<AndroidElement> driver = null;
    private AppiumServerFactory appiumFactory = null;
    private DriverFactory driverConnection = null;
    private String deviceNumber = AppProperties.getDevice();

    @Step("Set up appium server")
    @BeforeSuite
    public void startAppiumServerAndSetUpApplication() {
        this.appiumFactory = new AppiumServerFactory();
        this.appiumFactory.startAppiumServer();
        OcularConfig.mkdirsForScreens();
        connectAndSetUpDriver();
    }

    @BeforeMethod
    public void connectAndSetUpDriver() {
        if (driverConnection == null) {
            this.driverConnection = new DriverFactory(CapabilitiesFactory.getTruckerCapabilities());
            this.driver = DriverFactory.driver;
            initPages();
            if (!AppProperties.STATE_OF_DATA_CONNECTION) {
                AdbCmdExecutor.dataDisable(deviceNumber);
            }
            androidFunctions.turnOnWiFi();
            notificationPage.clearAllNotifications();
        }
    }

    @AfterSuite
    public void stopAppiumServerAndCleanUp() {
        AdbCmdExecutor.disconnect(deviceNumber);
        if (appiumFactory != null) {
            this.appiumFactory.stopAppiumServer();
        }
        this.driverConnection = null;
        this.appiumFactory = null;
    }

    public AndroidDriver<AndroidElement> getDriver() { return driver; }
}