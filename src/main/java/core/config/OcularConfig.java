package core.config;

import java.io.File;

public class OcularConfig {

    public static final String OCULAR_RESULT_PATH = "target/ocularResult/device_" + AppProperties.getDevice() + "/";
    public static final String OCULAR_SNAPSHOT_PATH = "src/main/resources/device_" + AppProperties.getDevice() + "_screens/snpshot/";
    public static final int SIMILARITY = 95;
    private static final boolean SAVE_SNAPSHOT = false;

    public static void mkdirsForScreens() {
        if (SAVE_SNAPSHOT) {
            mkdirsForResults();
            mkdirsForSnapshots();
        }
    }

    private static void mkdirsForResults() {
        if (new File(OCULAR_RESULT_PATH).mkdirs()) {
            System.out.println("Ocular: ścieżka zapisu obrazów źródłowych (snapshotów): " + OCULAR_RESULT_PATH);
        }
    }

    private static void mkdirsForSnapshots() {
        if (new File(OCULAR_SNAPSHOT_PATH).mkdirs()) {
            System.out.println("Ocular: ścieżka zapisu obrazów po porównaniu (rezultatów): " + OCULAR_SNAPSHOT_PATH);
        }
    }
}
