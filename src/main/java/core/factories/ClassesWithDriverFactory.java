package core.factories;

import core.helpers.AndroidFunctions;
import core.page.MainPage;
import core.page.android.NotificationPage;

public class ClassesWithDriverFactory {

    public static AndroidFunctions androidFunctions;
    public static NotificationPage notificationPage;
    public static MainPage mainPage;

    public static void initPages() {
        androidFunctions = new AndroidFunctions(DriverFactory.driver);
        notificationPage = new NotificationPage(DriverFactory.driver);
        mainPage = new MainPage(DriverFactory.driver);
    }
}
