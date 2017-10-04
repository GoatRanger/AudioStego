package dji.pilot.upgrade;

import dji.log.DJILogHelper;

public class d {
    public static void a(String str) {
    }

    public static void a(String str, String str2) {
        DJILogHelper.getInstance().LOGD(str, str2, false, false);
    }

    public static void a(String str, String str2, boolean z) {
        if (z) {
            DJILogHelper.getInstance().LOGD(str, str2, true, true);
        }
    }
}
