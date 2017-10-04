package dji.pilot.set;

import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;

public class f {
    public static void a(String str) {
        if (i.getInstance().c() == ProductType.LonganMobile) {
            try {
                Class.forName("dji.phone.preview.DJILPPreviewActivity").getMethod("reflectPostEvent", new Class[]{String.class}).invoke(null, new Object[]{str});
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            Class.forName("dji.device.activity.DJIPreviewActivityLongan").getMethod("reflectPostEvent", new Class[]{String.class}).invoke(null, new Object[]{str});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(int i) {
        if (i.getInstance().c() == ProductType.LonganMobile) {
            try {
                Class.forName("dji.phone.live.DJILPLivePresenter").getMethod("enterLiveShare", new Class[]{Integer.class}).invoke(null, new Object[]{Integer.valueOf(i)});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a() {
        if (i.getInstance().c() == ProductType.LonganMobile) {
            try {
                Class.forName("dji.phone.live.DJILPLivePresenter").getMethod("enterYoutubeLive", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
