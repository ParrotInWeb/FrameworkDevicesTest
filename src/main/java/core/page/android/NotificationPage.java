package core.page.android;

import core.config.project.ProjectProperties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import org.apache.log4j.Logger;

import java.util.List;

public class NotificationPage extends AndroidPagesAndUtils {

    final static Logger logger = Logger.getLogger(NotificationPage.class);
    public NotificationPage(AndroidDriver<AndroidElement> driver) { super(driver); }

    @Override
    protected void waitUntilPageIsLoaded() { }

    @AndroidFindBy(id = "android:id/status_bar_latest_event_content")
    private List<AndroidElement> allNotificationContainer;

    @HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "com.android.systemui:id/clear_all_button")
    @AndroidFindBy(id = "com.android.systemui:id/dismiss_text")
    @AndroidFindBy(id = "com.android.systemui:id/delete")
    @AndroidFindBy(id = "com.android.systemui:id/clear_all")
    private List<AndroidElement> clearAllBtn;

    public void clearAllNotifications() {
        if (ProjectProperties.cleanNotification) {
            driver.openNotifications();
            if (!clearAllBtn.isEmpty() && clearAllBtn.get(0).getAttribute("enabled").equals("true")) {
                clearAllBtn.get(0).click();
                logger.info("Notification was cleaned");
            } else {
                clickBack();
            }
        }
    }
}
