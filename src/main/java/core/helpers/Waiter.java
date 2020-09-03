package core.helpers;

import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class Waiter {

    private static int timeout = 10;
    private static int pollInterval = 100;
    final static Logger logger = Logger.getLogger(Waiter.class);

    public static void waitForVisibilityOfElement(AndroidElement element) {
        await("Wait for visibility of element")
                .atMost(timeout, TimeUnit.SECONDS)
                .pollInterval(pollInterval, TimeUnit.MILLISECONDS)
                .ignoreException(NoSuchElementException.class)
                .until(element::isDisplayed);
    }

    public static void waitUntilTextElementEqualsToExpectedText(AndroidElement element, String expectedText) {
        await("Wait for correct text element: '" + expectedText + "'")
                .atMost(timeout, TimeUnit.SECONDS)
                .pollInterval(pollInterval, TimeUnit.MILLISECONDS)
                .ignoreException(NoSuchElementException.class)
                .until(() -> element.getText().equals(expectedText));
    }

    public static void waitUntilButtonIsClickable(AndroidElement element, String expectedText) {
        await("Wait for clickable button: '" + expectedText + "'")
                .atMost(timeout, TimeUnit.SECONDS)
                .pollInterval(pollInterval, TimeUnit.MILLISECONDS)
                .ignoreException(NoSuchElementException.class)
                .until(() -> element.getText().equals(expectedText) && element.getAttribute("enabled").equals("true") && element.getAttribute("clickable").equals("true"));
    }

    public static void wait(int time) {
        logger.info("Sleep for milliseconds: " + time);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
