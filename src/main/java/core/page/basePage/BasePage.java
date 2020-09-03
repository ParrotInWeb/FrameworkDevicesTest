package core.page.basePage;

import com.testautomationguru.ocular.Ocular;
import core.config.OcularConfig;
import core.config.AppProperties;
import core.testTools.AllureAttachment;
import core.testTools.ScreenShooter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public abstract class BasePage {

    public AndroidDriver<AndroidElement> driver;

    public BasePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        waitUntilPageIsLoaded();
    }

    protected abstract void waitUntilPageIsLoaded();

    public ResourceBundle getTranslation() {
        String language = AppProperties.getDefaultLanguage();
        String locale = AppProperties.getDefaultLocale();
        return ResourceBundle.getBundle("MessagesBundle", new Locale(language, locale));
    }

    /**
     * Metoda asercyjna do robienia zrzutów ekranów pojedynczego elementu i porównaniu go z oryginałem
     * Page obsługujący poniższą metodę musi mieć andotację @Snap("#{CLASS_NAME}-#{DESCRIPTION}.png")
     *
     * @param element     Android element do weryfikacji
     * @param description przyjmuje dowolny tekst, który powinien określić nazwę testu np. 'Login_DlugaNazwa'
     */
    public void assertThatScreenOfElementIsCorrect(AndroidElement element, String description) {
        await("Wait for the differences to be checked by ocular")
                .atMost(2, TimeUnit.MINUTES)
                .pollInterval(100, TimeUnit.MILLISECONDS)
                .ignoreException(NoSuchElementException.class)
                .until(() -> {
                    AllureAttachment.attachScreen("DiffScreen", OcularConfig.OCULAR_RESULT_PATH + this.getClass().getSimpleName() + "-" + description + ".png");
                    AllureAttachment.attachScreen("ExpectedScreen", OcularConfig.OCULAR_SNAPSHOT_PATH + this.getClass().getSimpleName() + "-" + description + ".png");
                    ScreenShooter.takeScreenshot(driver, "Actual full screen");
                    return Ocular.snapshot()
                            .from(this)
                            .replaceAttribute("CLASS_NAME", this.getClass().getSimpleName())
                            .replaceAttribute("DESCRIPTION", description)
                            .sample()
                            .using(driver)
                            .element(element)
                            .compare()
                            .isEqualsImages();
                });
    }
}