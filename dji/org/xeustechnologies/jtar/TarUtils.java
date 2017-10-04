package org.xeustechnologies.jtar;

import java.io.File;

public class TarUtils {
    public static long calculateTarSize(File file) {
        return tarSize(file) + 1024;
    }

    private static long tarSize(File file) {
        if (file.isFile()) {
            return entrySize(file.length());
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return 512;
        }
        long j = 0;
        for (File file2 : listFiles) {
            if (file2.isFile()) {
                j += entrySize(file2.length());
            } else {
                j += tarSize(file2);
            }
        }
        return j;
    }

    private static long entrySize(long j) {
        long j2 = (0 + 512) + j;
        long j3 = j2 % 512;
        if (j3 > 0) {
            return j2 + (512 - j3);
        }
        return j2;
    }
}
