package core.testTools;

import core.config.DateTimeFormat;
import core.config.project.Paths;
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
    final static Logger logger = Logger.getLogger(ScreenShooter.class);

    @Step("Take screenshot")
    public static void takeScreenshot(AndroidDriver<AndroidElement> driver, String attachmentName) {
        String path = Paths.screenShots;
        LocalDateTime screenDate = LocalDateTime.now();
        final String screenDateName = screenDate.format(DateTimeFormat.yyyyMMddHHmmssSSS);
        final String fileName = attachmentName + "_" + screenDateName + ".png";

        final File scrFile = driver.getScreenshotAs(OutputType.FILE);

        try {
            logger.info("Take screenshot: " + path + fileName);
            FileUtils.copyFile(scrFile, new File(path + fileName));
        } catch (final IOException ex) {
            logger.error("Screenshot error: " + ex.getMessage());
        }

        AllureAttachment.attachScreen(attachmentName, path + fileName);
    }
}
