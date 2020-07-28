package test.base;

import core.factories.AppiumServerFactory;
import core.factories.CapabilitiesFactory;
import core.factories.DriverFactory;
import core.adb.AdbCommandExecutor;
import core.config.project.OcularConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.testng.annotations.*;

import static core.factories.ClassesWithDriverFactory.*;

public abstract class AppiumBaseTest {

    private AppiumServerFactory appiumFactory = null;
    private DriverFactory driverConnection = null;
    protected AndroidDriver<AndroidElement> driver = null;

    @Step("Set up appium server")
    @BeforeSuite
    public void startAppiumServerAndSetUpApplication() {
        this.appiumFactory = new AppiumServerFactory();
        this.appiumFactory.startAppiumServer();
        OcularConfig.mkdirForScreens();
        connectAndSetUpDriver();
    }

    @BeforeMethod
    public void connectAndSetUpDriver() {
        if (driverConnection == null) {
            this.driverConnection = new DriverFactory(CapabilitiesFactory.getTruckerCapabilities());
            this.driver = DriverFactory.driver;
            initPages();
            AdbCommandExecutor.dataDisabled();
            androidFunctions.turnOnWiFi();
            notificationPage.clearAllNotifications();
        }
    }

    @AfterSuite
    public void stopAppiumServerAndCleanUp() {
        AdbCommandExecutor.disconnectByIp();
        if (appiumFactory != null) {
            this.appiumFactory.stopAppiumServer();
        }
        this.driverConnection = null;
        this.appiumFactory = null;
    }

    public AndroidDriver<AndroidElement> getDriver() { return driver; }
}