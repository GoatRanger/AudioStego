package dji.midware.data.manager.P3;

import com.here.posclient.analytics.TrackerEvent;
import dji.log.DJILogHelper;
import dji.midware.data.a.a.b;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.thirdparty.a.c;

public class g extends h {
    public static boolean a = false;
    private static g b = null;
    private b c;
    private int d;
    private int e;

    public static synchronized g getInstance() {
        g gVar;
        synchronized (g.class) {
            if (b == null) {
                b = new g();
            }
            gVar = b;
        }
        return gVar;
    }

    public g() {
        this.c = b.RC;
        this.d = 0;
        this.e = 1000;
        this.enabledSetDataEvent = true;
    }

    public void a(boolean z) {
        this.isCheck = z;
    }

    public void a(int i) {
        if (this.curCameraEvent == o.ConnectOK) {
            this.handler.removeMessages(1);
            this.handler.sendEmptyMessageDelayed(1, (long) i);
        }
    }

    public void a() {
        this.handler.removeMessages(1);
    }

    public void b() {
        if (this.curCameraEvent == o.ConnectOK) {
            this.handler.removeMessages(1);
            this.handler.sendEmptyMessageDelayed(1, (long) this.e);
        }
    }

    public void a(b bVar) {
        this.c = bVar;
        if (bVar == b.MC) {
            if (this.curCameraEvent != o.ConnectOK) {
                this.curCameraEvent = o.ConnectOK;
                c.a().e(this.curCameraEvent);
            }
        } else if (bVar == b.IDLE && this.curCameraEvent != o.ConnectLose) {
            this.curCameraEvent = o.ConnectLose;
            c.a().e(this.curCameraEvent);
        }
    }

    public boolean c() {
        return this.curCameraEvent == o.ConnectOK;
    }

    protected void debug(b bVar) {
        if (this.d % 50000 == 0) {
            DJILogHelper.getInstance().LOGE("DJIPackManager", "pack senderType=" + bVar.f + " cmdSet=" + bVar.m, false, false);
        }
        this.d++;
        if (this.isCheck && (bVar.f == DeviceType.OFDM.value() || bVar.f == DeviceType.DM368.value() || bVar.f == DeviceType.FLYC.value() || bVar.f == DeviceType.CAMERA.value() || bVar.m == p.FLYC.a() || bVar.m == p.GIMBAL.a() || bVar.m == p.CAMERA.a() || bVar.m == p.CENTER.a() || (bVar.m == 0 && bVar.n == 39))) {
            if (this.curCameraEvent != o.ConnectOK) {
                this.curCameraEvent = o.ConnectOK;
                DataSpecialControl.getInstance().init().start(20);
                c.a().e(this.curCameraEvent);
            }
            if (this.c == b.RC) {
                this.handler.removeMessages(1);
                this.handler.sendEmptyMessageDelayed(1, (long) this.e);
            }
        }
        if (bVar.m != 5 || bVar.n == 6) {
        }
        if (bVar.m != 9 || bVar.n == 12) {
        }
        if (bVar.m != 8 || bVar.n == 1 || bVar.n == 2) {
        }
        if (bVar.m != 2 || bVar.n == 84 || bVar.n == 18 || bVar.n == 34 || bVar.n == 50 || bVar.n == 67) {
        }
        if (bVar.m != 0 || bVar.n == 34 || bVar.n == 35 || bVar.n == 36 || bVar.n == 37 || bVar.n == 64 || bVar.n == 65 || bVar.n == 66 || bVar.n == 67) {
        }
        if (bVar.m != 3 || bVar.n == 249) {
        }
        if (bVar.m == 9) {
        }
        if (bVar.m != 4 || bVar.n == 15 || bVar.n == 16 || bVar.n == 17 || bVar.n == 19) {
        }
        if (bVar.m != p.OSD.a() || bVar.n == 1) {
        }
        if (bVar.m != p.GIMBAL.a() || bVar.n == 39) {
        }
        if (bVar.m != p.GIMBAL.a() || bVar.n == 5) {
        }
        if (bVar.m != p.COMMON.a() || bVar.n == 39) {
        }
        if (bVar.m != 7 || bVar.n == 17) {
        }
        if (bVar.m != 2 || bVar.n == TrackerEvent.PositioningOfflineCommonIndoor) {
        }
        if (bVar.m != 2 || bVar.n == 136) {
        }
        if (bVar.f == DeviceType.GIMBAL.value() && bVar.n == 50 && bVar.m != p.COMMON.a()) {
        }
        if (bVar.f != DeviceType.OFDM.value() || bVar.n == 82) {
        }
        if (bVar.n == 62) {
        }
        if (a) {
            c.a().e(bVar);
        }
        if (bVar.n != 62) {
        }
    }
}
