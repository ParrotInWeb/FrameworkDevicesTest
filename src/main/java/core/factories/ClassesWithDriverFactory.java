package core.factories;

import core.page.android.AndroidPagesAndUtils;
import core.page.MainPage;
import core.page.android.NotificationPage;

public class ClassesWithDriverFactory {

    public static AndroidPagesAndUtils androidPagesAndUtils;
    public static NotificationPage notificationPage;
    public static MainPage mainPage;

    public static void initPages() {
        androidPagesAndUtils = new AndroidPagesAndUtils(DriverFactory.driver);
        notificationPage = new NotificationPage(DriverFactory.driver);
        mainPage = new MainPage(DriverFactory.driver);
    }
}
