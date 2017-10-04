package dji.device.pano;

import dji.log.DJILogHelper;
import dji.thirdparty.a.c;

public class Stitch {

    public enum a {
        INSTANCE;
        
        public static int b;
    }

    public static native int stitching(String[] strArr);

    public static native int stitchingM(String[] strArr);

    static {
        System.loadLibrary("stitch");
    }

    public static void sitichProgressUpgraded(double d) {
        DJILogHelper.getInstance().LOGD("", "DJIMethod : sitichProgressUpgraded (21)" + d, false, true);
        a aVar = a.INSTANCE;
        a.b = (int) (100.0d * d);
        c.a().e(a.INSTANCE);
    }
}
