package dji.pilot.visual.util;

import com.dji.a.a;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus;
import dji.midware.data.model.P3.DataEyePushVisionTip;
import dji.midware.data.model.P3.DataEyePushVisionTip.TrackingTipType;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.thirdparty.a.c;
import java.util.HashMap;

public class e {
    private static e a;
    private static final Object b = new Object();
    private boolean c = false;
    private long d = 0;
    private long e = 0;
    private int f = 1;

    public static e getInstance() {
        synchronized (b) {
            if (a == null) {
                a = new e();
            }
        }
        return a;
    }

    public void a() {
        synchronized (b) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
        }
        a(1);
        this.c = true;
    }

    private void a(int i) {
        DJILogHelper.getInstance().LOGE("tracklog", "report action=" + i, true, true);
        HashMap hashMap = new HashMap();
        hashMap.put("createtime", String.valueOf(System.currentTimeMillis()));
        hashMap.put("action", String.valueOf(i));
        hashMap.put("mode", String.valueOf(DataEyeGetPushTrackStatus.getInstance().getTrackingMode().a()));
        hashMap.put("circle", String.valueOf(this.f));
        hashMap.put("status", String.valueOf(DataEyeGetPushTrackStatus.getInstance().getRectMode()));
        hashMap.put("device_type", String.valueOf(i.getInstance().c()));
        hashMap.put("device_ver", dji.pilot.upgrade.e.getInstance().b());
        hashMap.put("device_sn", DataFlycActiveStatus.getInstance().getSN());
        hashMap.put("pro_id", String.valueOf(i.getInstance().c()));
        a.a(dji.publics.b.a.a.d, hashMap);
    }

    public void onEventBackgroundThread(DataEyePushVisionTip dataEyePushVisionTip) {
        DJILogHelper.getInstance().LOGE("tracklog", "onEventBackgroundThread type=" + dataEyePushVisionTip.b() + " takephoto=" + dataEyePushVisionTip.d(), true, true);
        TrackingTipType b = dataEyePushVisionTip.b();
        long currentTimeMillis;
        if (b == TrackingTipType.g) {
            currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.d > 60000) {
                this.d = currentTimeMillis;
                a(3);
            }
        } else if (b == TrackingTipType.f) {
            currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.e > 60000) {
                this.e = currentTimeMillis;
                a(2);
            }
        } else if (b == TrackingTipType.d) {
            if (this.c) {
                a(5);
                this.c = false;
            }
            this.f = 2;
        } else if (b == TrackingTipType.e) {
            this.f = 1;
        }
        if (dataEyePushVisionTip.d() == 1) {
            a(4);
        }
    }
}
