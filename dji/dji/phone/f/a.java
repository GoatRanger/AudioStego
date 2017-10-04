package dji.phone.f;

import android.content.Context;
import android.util.Log;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.phone.k.b;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class a {
    private static final String a = "DJILPGimbalStatusReceiver";
    private static final a b = new a();
    private static int e = R.string.longan_camera_mode_pano;
    private static int f = R.string.lp_tk_txt;
    private static int g = R.string.lp_camera_mode_long_exposure;
    private Context c;
    private d d = d.WORK_FINE_NORMAL;

    public static a getInstance() {
        return b;
    }

    public void a(Context context) {
        this.c = context;
        if (ServiceManager.getInstance().isConnected()) {
            this.d = d.WORK_FINE_NORMAL;
        }
        dji.f.a.a(this);
    }

    public void a() {
        this.c = null;
        dji.f.a.b(this);
    }

    public d b() {
        return this.d;
    }

    public void a(d dVar) {
        if (this.d != dVar) {
            this.d = dVar;
            c.a().e(this.d);
        }
    }

    public boolean a(d dVar, boolean z) {
        if (z) {
            if (this.d == d.WORK_FINE_NORMAL) {
                a(dVar);
                return true;
            }
        } else if (this.d == dVar) {
            a(d.WORK_FINE_NORMAL);
            return true;
        }
        return false;
    }

    public boolean c() {
        return this.d == d.SLEEP;
    }

    public void onEventMainThread(DataGimbalGetPushAbnormalStatus dataGimbalGetPushAbnormalStatus) {
        if (dataGimbalGetPushAbnormalStatus.getSleepMode() == 1) {
            a(d.SLEEP);
            Log.e(a, "HG300 gimbal status:" + this.d);
        } else if (dataGimbalGetPushAbnormalStatus.isMotorProtected()) {
            a(d.WORK_LOCKED);
            Log.e(a, "HG300 gimbal status:" + this.d);
        } else if (dataGimbalGetPushAbnormalStatus.isPhoneOutGimbal()) {
            a(d.WORK_NO_PHONE);
            Log.e(a, "HG300 gimbal status:" + this.d);
        } else if (this.d == d.NOT_CONNECTED || this.d == d.SLEEP || this.d == d.WORK_LOCKED || this.d == d.WORK_NO_PHONE) {
            a(d.WORK_FINE_NORMAL);
        }
        a(dataGimbalGetPushAbnormalStatus);
    }

    public void a(DataGimbalGetPushAbnormalStatus dataGimbalGetPushAbnormalStatus) {
        if (this.d != d.WORK_FINE_TRACK) {
            return;
        }
        if (dataGimbalGetPushAbnormalStatus.isYawLimitedInTracking() || dataGimbalGetPushAbnormalStatus.isPitchLimitedInTracking()) {
            b.showShort(R.string.lp_gimbal_limit_in_tracking);
        }
    }

    public void onEventBackgroundThread(p pVar) {
        if (pVar == p.b) {
            if (DataGimbalGetPushAbnormalStatus.getInstance().isGetted()) {
                onEventMainThread(DataGimbalGetPushAbnormalStatus.getInstance());
            }
        } else if (pVar == p.a) {
            a(d.NOT_CONNECTED);
        }
    }
}
