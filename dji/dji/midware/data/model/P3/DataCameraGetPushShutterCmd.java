package dji.midware.data.model.P3;

import android.util.Log;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.util.c;
import java.util.Arrays;

public class DataCameraGetPushShutterCmd extends n {
    private static DataCameraGetPushShutterCmd instance = null;
    private final String TAG = DataCameraGetPushShutterCmd.class.getSimpleName();

    public static synchronized DataCameraGetPushShutterCmd getInstance() {
        DataCameraGetPushShutterCmd dataCameraGetPushShutterCmd;
        synchronized (DataCameraGetPushShutterCmd.class) {
            if (instance == null) {
                instance = new DataCameraGetPushShutterCmd();
            }
            dataCameraGetPushShutterCmd = instance;
        }
        return dataCameraGetPushShutterCmd;
    }

    public int getShutterType() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected boolean isChanged(byte[] bArr) {
        if (this.pack.m == 2 && this.pack.n == 124) {
            Log.d(this.TAG, "isChanged: recData" + c.i(this.pack.r));
            start();
            return true;
        } else if (Arrays.equals(this._recData, bArr)) {
            return false;
        } else {
            return true;
        }
    }

    public void start() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.b.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aL.a();
        super.start(cVar);
    }

    protected void doPack() {
        this._sendData = new byte[]{(byte) 0, (byte) 0};
    }
}
