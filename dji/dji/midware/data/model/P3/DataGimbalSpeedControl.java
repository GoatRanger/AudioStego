package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataGimbalSpeedControl extends n implements e {
    private static DataGimbalSpeedControl instance = null;
    private boolean isGetPermission;
    private int pitch;
    private int roll;
    private int yaw;

    public static synchronized DataGimbalSpeedControl getInstance() {
        DataGimbalSpeedControl dataGimbalSpeedControl;
        synchronized (DataGimbalSpeedControl.class) {
            if (instance == null) {
                instance = new DataGimbalSpeedControl();
            }
            dataGimbalSpeedControl = instance;
        }
        return dataGimbalSpeedControl;
    }

    public DataGimbalSpeedControl setPitch(int i) {
        this.pitch = i;
        return this;
    }

    public DataGimbalSpeedControl setRoll(int i) {
        this.roll = i;
        return this;
    }

    public DataGimbalSpeedControl setYaw(int i) {
        this.yaw = i;
        return this;
    }

    public DataGimbalSpeedControl setPermission(boolean z) {
        this.isGetPermission = z;
        return this;
    }

    protected void doPack() {
        int i = 0;
        this._sendData = new byte[7];
        System.arraycopy(c.b(this.yaw), 0, this._sendData, 0, 2);
        System.arraycopy(c.b(this.roll), 0, this._sendData, 2, 2);
        System.arraycopy(c.b(this.pitch), 0, this._sendData, 4, 2);
        byte[] bArr = this._sendData;
        if (this.isGetPermission) {
            i = 1;
        }
        bArr[6] = (byte) (i << 7);
    }

    public void start() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.SpeedControl.a();
        super.start(cVar);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.SpeedControl.a();
        start(cVar, dVar);
    }
}
