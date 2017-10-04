package dji.device.camera.a;

import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.thirdparty.a.c;

public class a {
    private static final String b = "LonganCameraModeManager";
    private static a c = null;
    boolean a = false;
    private a d = a.TAKEPHOTO;

    public enum a {
        TAKEPHOTO,
        RECORD
    }

    private a() {
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    public void a() {
        if (!c.a().c(this)) {
            c.a().a(this);
            if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
                onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
            }
        }
    }

    public void b() {
        c = null;
        c.a().d(this);
    }

    public boolean c() {
        return this.a;
    }

    public a d() {
        return this.d;
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        this.a = true;
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (mode == MODE.TAKEPHOTO) {
            if (this.d != a.TAKEPHOTO) {
                this.d = a.TAKEPHOTO;
            } else {
                return;
            }
        } else if (mode == MODE.RECORD) {
            if (this.d != a.RECORD) {
                this.d = a.RECORD;
            } else {
                return;
            }
        }
        c.a().e(this);
    }
}
