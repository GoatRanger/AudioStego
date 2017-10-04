package com.nokia.maps;

import android.content.Context;
import com.nokia.maps.annotation.Online;
import dji.pilot.college.b.b;
import java.io.File;

@Online
class PositioningResourceManager {
    private static final String a = PositioningResourceManager.class.getName();

    private static native boolean deployToDisk(String str, boolean z);

    PositioningResourceManager() {
    }

    public static boolean a(Context context) {
        File file = new File(context.getDir("data", 0), b.g);
        if (!file.exists() && !file.mkdirs()) {
            bj.c(a, "Error: Could not create path '%s'", new Object[]{file.getAbsolutePath()});
            return false;
        } else if (bf.a("PositioningResourcePkg").booleanValue()) {
            bj.d(a, "Deplying Positioning resource to '%s'", new Object[]{file.getAbsolutePath()});
            return deployToDisk(file.getAbsolutePath(), false);
        } else {
            bj.b(a, "Library (PositioningResourcePkg) failed to load ...", new Object[0]);
            return false;
        }
    }
}
