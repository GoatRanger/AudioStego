package dji.pilot.reflect;

import dji.pilot.fpv.d.c.q;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;

public class FpvReflect implements q, s {
    public static boolean isMerge = true;

    public static Class<?> getLonganClass() {
        try {
            return Class.forName("dji.device.activity.DJIPreviewActivityLongan");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Class<?> getLpClass() {
        try {
            return Class.forName("dji.phone.preview.DJILPPreviewActivity");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Class<?> getLonganActiveClass() {
        try {
            return Class.forName("dji.device.active.OsmoActiveActivity");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void flurryCameraExposure() {
        e.c(s.dn);
    }

    public static void flurryGimbalDrag() {
        e.c(s.dp);
    }

    public static void flurryOsmoTimePlace() {
        e.b(q.a);
    }

    public static void flurryOsmoPano() {
        e.b(q.b);
    }

    public static void flurryOsmoSafiPano() {
        e.b(q.c);
    }

    public static void flurryOsmoGimbalMode() {
        e.b(q.d);
    }

    public static void flurryOsmoRecenter() {
        e.b(q.e);
    }
}
