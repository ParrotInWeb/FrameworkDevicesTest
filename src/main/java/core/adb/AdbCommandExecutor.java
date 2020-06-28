package core.adb;

import core.config.project.ProjectProperties;
import core.factories.AppiumServerFactory;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class AdbCommandExecutor {

    final static Logger logger = Logger.getLogger(AppiumServerFactory.class);

    public static String executeCommand(String command) {
        try {
            final Process exec = Runtime.getRuntime().exec(command);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

            LinkedList<String> readerList = new LinkedList<>();
            while ((bufferedReader.readLine()) != null) {
                readerList.add(bufferedReader.readLine());
            }
            return String.join("\n", readerList);

        } catch (IOException ex) {
            logger.info("Doesn't execute command: '" + command + "'");
            return "";
        }
    }

    public static void adbConnect() {
        await("Wait until device is connected")
                .atMost(60, TimeUnit.SECONDS)
                .pollInterval(5, TimeUnit.SECONDS)
                .until(() -> {
                    logger.info("Try to connect device: " + ProjectProperties.getDevice());
                    connectByIp();
                    return isDeviceConnected();
                });
    }


    public static void connectByIp() {
        if (ProjectProperties.getDevice().contains(".")) {
            AdbCommandExecutor.executeCommand(AdbCommandsRepository.adbConnect);
        }
    }

    public static void disconnectByIp() {
        if (ProjectProperties.getDevice().contains(".")) {
            AdbCommandExecutor.executeCommand(AdbCommandsRepository.adbDisconnect);
        }
    }

    public static Boolean isDeviceConnected() {
        String listOfDevices = AdbCommandExecutor.executeCommand("adb devices").replaceAll("[ \t]", "");
        return listOfDevices.contains(ProjectProperties.getDevice() + "device");
    }

    public static void dataDisabled() {
        if (ProjectProperties.changeDataConnection)
            logger.info("Disable data connection");
            AdbCommandExecutor.executeCommand(AdbCommandsRepository.dataDisable);
    }

    public static boolean isWifiOn() {
        String ipWifi = AdbCommandExecutor.executeCommand(AdbCommandsRepository.getWifiIp);
        return !ipWifi.isEmpty();
    }
}