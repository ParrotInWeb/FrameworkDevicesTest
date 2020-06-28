package test;

import core.testTools.TestNgCoreListener;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.base.AppiumBaseTest;

import static core.factories.ClassesWithDriverFactory.mainPage;

@Listeners(TestNgCoreListener.class)
@Feature("Testy strony głównej")
public class MainPageTest extends AppiumBaseTest {

    @AfterMethod
    public void resetApp() {
        super.driver.resetApp();
    }

    @Test
    @Description("MainPageTest: Check visibility of all elements")
    public void checkVisibilityOfAllElements() {
        mainPage
                .assertThatLogoIsDisplayed();
    }
}
