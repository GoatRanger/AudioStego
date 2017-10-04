package dji.device.camera.a;

import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;

public class b {
    private static final String a = "LonganPhotoStateManager";
    private static b b = null;
    private a c = a.PHOTO_NOT;
    private c d = c.NOT_TIMING;
    private b e = b.SAVING_NOT;

    public enum a {
        PHOTO_NOT,
        PHOTO_ING
    }

    public enum b {
        SAVING_NOT,
        SAVING
    }

    public enum c {
        TIMING,
        NOT_TIMING
    }

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b();
            }
            bVar = b;
        }
        return bVar;
    }

    private b() {
    }

    public void a() {
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    public void b() {
        b = null;
        dji.thirdparty.a.c.a().d(this);
    }

    public a c() {
        return this.c;
    }

    public c d() {
        return this.d;
    }

    public b e() {
        return this.e;
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.getMode() != MODE.RECORD) {
            a aVar = a.PHOTO_NOT;
            switch (dataCameraGetPushStateInfo.getPhotoState()) {
                case NO:
                    aVar = a.PHOTO_NOT;
                    break;
                default:
                    aVar = a.PHOTO_ING;
                    break;
            }
            c cVar = dataCameraGetPushStateInfo.getIsTimePhotoing() ? c.TIMING : c.NOT_TIMING;
            b bVar = dataCameraGetPushStateInfo.getIsStoring() ? b.SAVING : b.SAVING_NOT;
            if (cVar != this.d) {
                this.d = cVar;
                dji.thirdparty.a.c.a().e(this.d);
            }
            if (aVar != this.c) {
                this.c = aVar;
                dji.thirdparty.a.c.a().e(this.c);
            }
            if (this.e != bVar) {
                this.e = bVar;
                dji.thirdparty.a.c.a().e(this.e);
            }
            DJILogHelper.getInstance().LOGD(a, "DataCameraGetPushStateInfotimimg state" + cVar);
        }
    }
}
