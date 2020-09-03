package core.factories;

import core.config.AppProperties;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;

public class AppiumServerFactory {

    final static Logger logger = Logger.getLogger(AppiumServerFactory.class);

    private static final String APPIUM_PATH = AppProperties.getAppiumPath();
    private static final String NODE_PATH = AppProperties.getNodeJsPath();
    private static AppiumDriverLocalService service;

    @Step("Start Appium server")
    public void startAppiumServer() {

        service = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(NODE_PATH))
                .withAppiumJS(new File(APPIUM_PATH))
                .withArgument(GeneralServerFlag.LOG_LEVEL, "warn")
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER)
                .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, AppProperties.getBootstrapPort())
                .usingAnyFreePort()
                .build();

        logger.info("Start Appium server");
        service.start();
        logger.info("URL Appium server: " + service.getUrl());
    }

    public static URL getUrl() {
        return service.getUrl();
    }

    @Step("Stop Appium server")
    public void stopAppiumServer() {
        logger.info("Stop Appium server");
        service.stop();
    }
}