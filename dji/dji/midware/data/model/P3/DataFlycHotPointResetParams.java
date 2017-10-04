package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.ROTATION_DIR;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycHotPointResetParams extends n implements e {
    private static DataFlycHotPointResetParams instance = null;
    private float angleSpeed = 0.0f;
    private ROTATION_DIR rotationDir = ROTATION_DIR.Anti_Clockwise;

    public static synchronized DataFlycHotPointResetParams getInstance() {
        DataFlycHotPointResetParams dataFlycHotPointResetParams;
        synchronized (DataFlycHotPointResetParams.class) {
            if (instance == null) {
                instance = new DataFlycHotPointResetParams();
            }
            dataFlycHotPointResetParams = instance;
        }
        return dataFlycHotPointResetParams;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataFlycHotPointResetParams setVelocity(float f) {
        this.angleSpeed = f;
        return this;
    }

    public DataFlycHotPointResetParams setRotationDir(ROTATION_DIR rotation_dir) {
        this.rotationDir = rotation_dir;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.ar.a();
        super.start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[5];
        this._sendData[0] = (byte) this.rotationDir.value();
        System.arraycopy(dji.midware.util.c.a(this.angleSpeed), 0, this._sendData, 1, 4);
    }
}
