package com.nokia.maps;

import android.content.Context;
import com.nokia.maps.annotation.Online;
import java.io.File;
import java.io.IOException;

@Online
class MapsEngineResourceManager {
    private static final String a = MapsEngineResourceManager.class.getName();

    private static native boolean deployToDisk(String str, boolean z);

    MapsEngineResourceManager() {
    }

    public static void a(File file) throws IOException {
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                for (String file2 : list) {
                    File file3 = new File(file, file2);
                    if (file3.isDirectory()) {
                        a(file3);
                    } else if (!file3.delete()) {
                        throw new IOException("Failed to delete file: " + file3.getName());
                    }
                }
            }
            if (!file.delete()) {
                throw new IOException("Failed to delete dir: " + file.getName());
            }
        }
    }

    static boolean a(Context context, String str) {
        bj.a(MapsEngineResourceManager.class.getName(), "DestDir=" + str, new Object[0]);
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            bj.c(a, "Failed to mkdirs() for %s", new Object[]{str});
            return false;
        } else if (bf.a("MapsEngineResourcePkg").booleanValue()) {
            return deployToDisk(str, true);
        } else {
            bj.b("MapsEngineResourceManager", "Library (MapsEngineResourcePkg) failed to load ...", new Object[0]);
            return false;
        }
    }
}
