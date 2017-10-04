package dji.device.camera.a;

import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.thirdparty.a.c;

public class d {
    private static final String a = "LonganRecordStateManager";
    private static d b = null;
    private a c = a.NO;

    public enum a {
        NO,
        START,
        RECORDING,
        STOP
    }

    public static synchronized d getInstance() {
        d dVar;
        synchronized (d.class) {
            if (b == null) {
                b = new d();
            }
            dVar = b;
        }
        return dVar;
    }

    private d() {
    }

    public void a() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    public void b() {
        b = null;
        c.a().d(this);
    }

    public a c() {
        return this.c;
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.getMode() != MODE.TAKEPHOTO) {
            switch (dataCameraGetPushStateInfo.getRecordState()) {
                case NO:
                    if (this.c != a.NO) {
                        this.c = a.NO;
                        break;
                    }
                    return;
                case START:
                    if (this.c != a.START) {
                        this.c = a.START;
                        break;
                    }
                    return;
                case STARTING:
                    if (this.c != a.RECORDING) {
                        this.c = a.RECORDING;
                        break;
                    }
                    return;
                case STOP:
                    if (this.c != a.STOP) {
                        this.c = a.STOP;
                        break;
                    }
                    return;
                default:
                    if (this.c != a.NO) {
                        this.c = a.NO;
                        break;
                    }
                    return;
            }
            c.a().e(this.c);
        }
    }
}
