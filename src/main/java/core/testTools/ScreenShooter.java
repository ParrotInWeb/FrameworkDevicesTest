package core.testTools;

import core.formatters.DateTimeFormat;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ScreenShooter {

    static final Logger logger = Logger.getLogger(ScreenShooter.class);

    @Step("Take screenshot")
    public static void takeScreenshot(AndroidDriver<AndroidElement> driver, String attachmentName) {
        String screenShots = "target/screens/";
        String fileName = setFileName(attachmentName);

        try {
            logger.info("Take screenshot: " + screenShots + fileName);
            FileUtils.copyFile(getSourceFile(driver), new File(screenShots + fileName));
        } catch (final IOException ex) {
            logger.error("Screenshot error: " + ex.getMessage());
        }

        AllureAttachment.attachScreen(attachmentName, screenShots + fileName);
    }

    private static String setFileName(String attachmentName) {
        LocalDateTime screenDate = LocalDateTime.now();
        String screenDateName = screenDate.format(DateTimeFormat.yyyyMMddHHmmssSSS);
        return attachmentName + "_" + screenDateName + ".png";

    }

    private static File getSourceFile(AndroidDriver<AndroidElement> driver) {
        return driver.getScreenshotAs(OutputType.FILE);
    }
}
