package core.config.project;

import java.io.File;

public class OcularConfig {

    public static final int similarity = 95;
    public static final boolean saveSnapshot = false;

    public static void mkdirForScreens() {
        if (saveSnapshot) {
            System.out.println("Ocular: ścieżka zapisu obrazów źródłowych (snapshotów): " + Paths.ocularResultPath);
            System.out.println("Ocular: ścieżka zapisu obrazów po porównaniu (rezultatów): " + Paths.ocularSnapshotPath);
            final boolean mkdirForResult = new File(Paths.ocularResultPath).mkdirs();
            final boolean mkdirForSnapSHot = new File(Paths.ocularSnapshotPath).mkdirs();
        }
    }
}
