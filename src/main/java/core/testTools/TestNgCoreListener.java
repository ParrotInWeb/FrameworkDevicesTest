package core.testTools;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import test.base.AppiumBaseTest;

public class TestNgCoreListener extends TestListenerAdapter {

    final static Logger logger = Logger.getLogger(TestNgCoreListener.class);

    @Override
    public void onTestFailure(final ITestResult tr) {
        final Throwable result = tr.getThrowable();
        if (result != null) {
            logger.error("Test Failed with exception: " + result);
        } else {
            logger.error("Test Failed");
        }
        takeScreenShot(tr, "Failure");
    }

    @Override
    public void onTestSuccess(final ITestResult tr) {
        logger.info("Test Passed");
    }

    @Override
    public void onTestSkipped(final ITestResult tr) {
        final Throwable result = tr.getThrowable();
        if (result != null) {
            logger.warn("Test Skipped with exception: " + result);
        } else {
            logger.warn("Test Skipped");
        }
        takeScreenShot(tr, "Skipped");
    }

    private void takeScreenShot(ITestResult tr, String attachmentName) {
        try {
            logger.info("Taking screenshot");
            ScreenShooter.takeScreenshot(getActualWebBrowser(tr), attachmentName);
        } catch (final Exception ex) {
            logger.warn(ex.toString());
        }
    }

    private AndroidDriver<AndroidElement> getActualWebBrowser(ITestResult tr) {
        Object currentClass = tr.getInstance();
        return ((AppiumBaseTest) currentClass).getDriver();
    }
}