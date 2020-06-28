package core.page.android;

import core.adb.AdbCommandExecutor;
import core.config.project.ProjectProperties;
import core.page.basePage.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class AndroidPagesAndUtils extends BasePage {

    final static Logger logger = Logger.getLogger(AndroidPagesAndUtils.class);
    public AndroidPagesAndUtils(AndroidDriver<AndroidElement> driver) { super(driver); }

    @Override
    protected void waitUntilPageIsLoaded() {}

    @Step("Click android BACk")
    public void clickBack() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Step("WiFi enabled")
    public void wiFiEnabled() {
        if (ProjectProperties.changeWiFiConnection && !driver.getConnection().isWiFiEnabled()) {
            driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
            await("Wait for enable wifi")
                    .pollInterval(100, TimeUnit.MILLISECONDS)
                    .atMost(30, TimeUnit.SECONDS)
                    .until(() -> driver.getConnection().isWiFiEnabled() && AdbCommandExecutor.isWifiOn());
        }
    }

    @Step("WiFi disabled")
    public void wiFiDisabled() {
        if (ProjectProperties.changeWiFiConnection && driver.getConnection().isWiFiEnabled()) {
            driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
            await("Wait for disable wifi")
                    .pollInterval(100, TimeUnit.MILLISECONDS)
                    .atMost(30, TimeUnit.SECONDS)
                    .until(() -> !driver.getConnection().isWiFiEnabled());
        }
    }
}
