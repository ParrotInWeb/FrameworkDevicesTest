package core.page;

import core.helpers.Waiter;
import core.page.basePage.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPage extends BasePage {

    final static Logger logger = Logger.getLogger(MainPage.class);
    public MainPage(AndroidDriver< AndroidElement > driver) { super(driver); }

    @Override
    protected void waitUntilPageIsLoaded() {
        Waiter.waitForElementVisibility(logo);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='AIDA64']")
    private AndroidElement logo;

    @Step("MainPageTest: Is logo displayed")
    public MainPage assertThatLogoIsDisplayed() {
        assertThat(logo.isDisplayed()).as("Is logo displayed").isTrue();
        return this;
    }
}