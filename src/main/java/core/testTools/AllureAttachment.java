package core.testTools;

import io.qameta.allure.Allure;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AllureAttachment {
    final static Logger logger = Logger.getLogger(ScreenShooter.class);

    public static void attachScreen(String attachmentName, String fileName) {
        final Path content = Paths.get(fileName);
        try (InputStream stream = Files.newInputStream(content)) {
            Allure.addAttachment(attachmentName, stream);
        } catch (final IOException ex) {
            logger.error("AllureAttachment error: " + ex.getMessage());
        }
    }
}
