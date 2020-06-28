package core.config.project;

import java.io.File;

public class Paths {

    private static final String sep = File.separator;

    public static final String screenShots = "target" + sep + "screens" + sep;
    public static final String ocularResultPath = "target" + sep + "ocularResult" + sep + "device_" + ProjectProperties.getDevice() + sep;
    public static final String ocularSnapshotPath = "src" + sep + "main" + sep + "resources" + sep + "device_" + ProjectProperties.getDevice() + "_screens" + sep + "snpshot" + sep;


}
