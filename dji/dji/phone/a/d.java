package dji.phone.a;

import dji.phone.d.a.b;
import dji.phone.d.c;
import dji.phone.f.a;

public class d {
    private static final String a = "DJILPSpecialLogic";

    public static boolean a() {
        if (a.getInstance().c()) {
            return false;
        }
        if (dji.phone.d.d.getInstance().c() == c.a.CAMERASTATE_RECORD_PREVIEW || dji.phone.d.d.getInstance().c() == c.a.CAMERASTATE_RECORDING) {
            if (dji.phone.d.d.getInstance().i() == dji.phone.d.a.c.TIMELAPSE_MOTION || dji.phone.d.d.getInstance().i() == dji.phone.d.a.c.TIMELAPSE_STATIONARY) {
                return false;
            }
        } else if (dji.phone.d.d.getInstance().h() == b.LONGEXPOSURE) {
            return false;
        } else {
            if (dji.phone.d.d.getInstance().h() == b.PANO) {
                return false;
            }
        }
        return true;
    }
}
