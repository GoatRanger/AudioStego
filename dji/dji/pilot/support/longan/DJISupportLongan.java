package dji.pilot.support.longan;

import android.content.Context;
import dji.pilot2.library.a;
import dji.playback.entryActivity.e;
import dji.thirdparty.a.c;

public class DJISupportLongan {
    public static void cleanCache() {
        c.a().e(a.VideoAllDelete);
    }

    public static void reflectNotifyNewMedia(String str) {
        c.a().e(e.a.EventHGPhotoCreate);
    }

    public static void resetBeginnerGuide(Context context) {
        try {
            Class cls = Class.forName("dji.pilot.set.AppSettingsHelper");
            cls.getMethod("resetBeginnerGuide", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
