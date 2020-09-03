package core.page.android;

import core.config.AppProperties;
import core.helpers.AndroidFunctions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import org.apache.log4j.Logger;

import java.util.List;

public class NotificationPage extends AndroidFunctions {

    final static Logger logger = Logger.getLogger(NotificationPage.class);

    public NotificationPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Override
    protected void waitUntilPageIsLoaded() {
    }

    @AndroidFindBy(id = "android:id/status_bar_latest_event_content")
    private List<AndroidElement> allNotificationContainer;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.android.systemui:id/clear_all_button")
    @AndroidFindBy(id = "com.android.systemui:id/dismiss_text")
    @AndroidFindBy(id = "com.android.systemui:id/delete")
    @AndroidFindBy(id = "com.android.systemui:id/clear_all")
    private List<AndroidElement> clearAllBtn;

    public void clearAllNotifications() {
        if (AppProperties.STATE_OF_NOTIFICATIONS) {
            driver.openNotifications();
            if (areNotificationToClear()) {
                getClearNotificationButton().click();
                logger.info("Notification was cleared");
            } else {
                clickBack();
            }
        }
    }

    public boolean areNotificationToClear() {
        return !clearAllBtn.isEmpty() && getClearNotificationButton().getAttribute("enabled").equals("true");
    }

    public AndroidElement getClearNotificationButton() {
        return clearAllBtn.get(0);
    }

}
