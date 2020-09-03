package core.adb;

import core.factories.AppiumServerFactory;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static core.adb.AdbCommand.*;
import static org.awaitility.Awaitility.await;

public class AdbCmdExecutor {

    static Controller controller = new Controller();
    final static Logger logger = Logger.getLogger(AppiumServerFactory.class);

    public static void connect(String deviceNumber) {
        await("Wait until device " + deviceNumber + " is connected")
                .atMost(60, TimeUnit.SECONDS)
                .pollInterval(5, TimeUnit.SECONDS)
                .until(() -> {
                    connectByIpIfSet(deviceNumber);
                    return isDeviceConnected(deviceNumber);
                });
    }

    private static void connectByIpIfSet(String deviceNumber) {
        if (deviceNumber.contains(".")) {
            controller.setCommand(CONNECT, deviceNumber);
            controller.executeCommand();
        }
    }

    private static boolean isDeviceConnected(String deviceNumber) {
        if (!getListOfDevices().contains(deviceNumber + "device")) {
            logger.error("Device " + deviceNumber + " is not connected");
            return false;
        }
        return true;
    }

    private static String getListOfDevices() {
        controller.setCommand(DEVICE);
        return controller
                .executeCommandAndGetResult()
                .replaceAll("[ \t\n]", "");
    }

    public static void disconnect(String deviceNumber) {
        if (isDeviceConnected(deviceNumber)) {
            controller.setCommand(DISCONNECT, deviceNumber);
            controller.executeCommand();
        }
    }

    public static void reboot(String deviceNumber) {
        if (isDeviceConnected(deviceNumber)) {
            controller.setCommand(REBOOT, deviceNumber);
            controller.executeCommand();
        }
    }

    public static void dataDisable(String deviceNumber) {
        if (isDeviceConnected(deviceNumber)) {
            logger.info("Disable Data Connection");
            controller.setCommand(DATA_DISABLE, deviceNumber);
            controller.executeCommand();
        }
    }

    public static boolean isWiFiIpSet(String deviceNumber) {
        if (isDeviceConnected(deviceNumber)) {
            controller.setCommand(GET_WIFI_IP, deviceNumber);
            String wiFiIp = controller.executeCommandAndGetResult();
            logger.info("Address IP of Wifi: " + wiFiIp);
            return !wiFiIp.isEmpty();
        }
        return false;
    }
}