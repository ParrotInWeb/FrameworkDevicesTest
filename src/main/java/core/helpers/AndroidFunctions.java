package core.helpers;

import core.adb.AdbCmdExecutor;
import core.config.AppProperties;
import core.page.basePage.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class AndroidFunctions extends BasePage {

    public AndroidFunctions(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Override
    protected void waitUntilPageIsLoaded() {
    }

    @Step("Click android BACk")
    public void clickBack() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Step("WiFi enabled")
    public void turnOnWiFi() {
        if (AppProperties.STATE_OF_WI_FI_CONNECTION && !driver.getConnection().isWiFiEnabled()) {
            driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
            await("Wait for enable wifi")
                    .pollInterval(100, TimeUnit.MILLISECONDS)
                    .atMost(30, TimeUnit.SECONDS)
                    .until(() -> driver.getConnection().isWiFiEnabled() && AdbCmdExecutor.isWiFiIpSet(AppProperties.getDevice()));
        }
    }

    @Step("WiFi disabled")
    public void turnOffWiFi() {
        if (AppProperties.STATE_OF_WI_FI_CONNECTION && driver.getConnection().isWiFiEnabled()) {
            driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
            await("Wait for disable wifi")
                    .pollInterval(100, TimeUnit.MILLISECONDS)
                    .atMost(30, TimeUnit.SECONDS)
                    .until(() -> !driver.getConnection().isWiFiEnabled());
        }
    }
}
