package core.adb;

import core.config.project.ProjectProperties;

public class AdbCommandsRepository {

    public static final String adbConnect = "adb connect " + ProjectProperties.getDevice();
    public static final String adbDisconnect = "adb disconnect";
    public static final String dataDisable = "adb -s " + ProjectProperties.getDevice() + " shell svc data disable";
    public static final String getWifiIp = "adb -s " + ProjectProperties.getDevice() + " shell ip route";
    public static final String reboot = "adb -s " + ProjectProperties.getDevice() + " reboot";

}