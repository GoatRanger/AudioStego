package dji.device.gimbal.control;

import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.thirdparty.a.c;

public class d {
    private static final boolean a = false;
    private static final String b = "LonganGimbalStatusManager";
    private static a c = a.NORMAL;
    private static d d = null;

    public enum a {
        NORMAL,
        FLASHLIGHT,
        UPSIGHTDOWN,
        PORTRAIN
    }

    private d() {
    }

    public static d getInstance() {
        if (d == null) {
            d = new d();
        }
        return d;
    }

    public void a() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        DJILogHelper.getInstance().LOGD(b, "rigeisted", false, false);
    }

    public void b() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public a c() {
        return c;
    }

    public void onEventMainThread(DataGimbalGetPushAbnormalStatus dataGimbalGetPushAbnormalStatus) {
        a aVar;
        DJILogHelper.getInstance().LOGD(b, "received", false, false);
        if (dataGimbalGetPushAbnormalStatus.isPortrait()) {
            aVar = a.PORTRAIN;
        } else if (dataGimbalGetPushAbnormalStatus.isInFlashlight()) {
            aVar = a.FLASHLIGHT;
        } else if (dataGimbalGetPushAbnormalStatus.getVerticalDirection() == 0) {
            aVar = a.UPSIGHTDOWN;
        } else {
            aVar = a.NORMAL;
        }
        if (aVar != c) {
            c = aVar;
            c.a().e(this);
            DJILogHelper.getInstance().LOGD(b, "posted" + c, false, false);
        }
    }
}
