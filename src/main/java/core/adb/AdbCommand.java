package core.adb;

public class AdbCommand {

    public static final String CONNECT = "adb connect %s";
    public static final String DISCONNECT = "adb disconnect %s";
    public static final String DATA_DISABLE = "adb -s %s shell svc data disable";
    public static final String DEVICE = "adb devices";
    public static final String GET_WIFI_IP = "adb -s %s shell ip route";
    public static final String REBOOT = "adb -s %s reboot";
    public static final String ROOT = "adb -s %s root";
}