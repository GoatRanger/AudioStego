package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetVOutParams extends n implements e {
    private static DataCameraSetVOutParams a = null;
    private boolean b = false;
    private boolean c = false;
    private LCDFormat d = LCDFormat.AUTO_NO_GLASS_CONNECTED;

    public enum LCDFormat {
        AUTO,
        R1280x720_FPS30,
        R1280x720_FPS60,
        R1920x1080_FPS30,
        R1920x1080_FPS60,
        AUTO_GLASS_CONNECTED,
        AUTO_NO_GLASS_CONNECTED,
        HD_FORMAT
    }

    public static synchronized DataCameraSetVOutParams getInstance() {
        DataCameraSetVOutParams dataCameraSetVOutParams;
        synchronized (DataCameraSetVOutParams.class) {
            if (a == null) {
                a = new DataCameraSetVOutParams();
            }
            dataCameraSetVOutParams = a;
        }
        return dataCameraSetVOutParams;
    }

    public DataCameraSetVOutParams a(boolean z) {
        this.b = z;
        return this;
    }

    public DataCameraSetVOutParams b(boolean z) {
        if (z) {
            this.d = LCDFormat.HD_FORMAT;
        } else {
            this.d = LCDFormat.AUTO_NO_GLASS_CONNECTED;
        }
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.ah.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[3];
        this._sendData[0] = (byte) 0;
        if (this.b) {
            this._sendData[0] = (byte) 1;
        }
        if (this.c) {
            byte[] bArr = this._sendData;
            bArr[0] = (byte) (bArr[0] | 2);
        }
        this._sendData[1] = (byte) this.d.ordinal();
        this._sendData[2] = (byte) 0;
    }
}
