package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;

public class DataFlycJoystick extends n implements b {
    private static DataFlycJoystick instance = null;
    private byte flag;
    private float pitch;
    private float roll;
    private float throttle;
    private float yaw;

    public static synchronized DataFlycJoystick getInstance() {
        DataFlycJoystick dataFlycJoystick;
        synchronized (DataFlycJoystick.class) {
            if (instance == null) {
                instance = new DataFlycJoystick();
            }
            dataFlycJoystick = instance;
        }
        return dataFlycJoystick;
    }

    public DataFlycJoystick setPitch(float f) {
        this.pitch = f;
        return this;
    }

    public DataFlycJoystick setRoll(float f) {
        this.roll = f;
        return this;
    }

    public DataFlycJoystick setYaw(float f) {
        this.yaw = f;
        return this;
    }

    public DataFlycJoystick setThrottle(float f) {
        this.throttle = f;
        return this;
    }

    public DataFlycJoystick setFlag(byte b) {
        this.flag = b;
        return this;
    }

    public void start() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = q.b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.at.a();
        cVar.p = getSendData();
        super.start(cVar);
    }

    protected void doPack() {
        this._sendData = new byte[17];
        this._sendData[0] = this.flag;
        System.arraycopy(dji.midware.util.c.a(this.roll), 0, this._sendData, 1, 4);
        System.arraycopy(dji.midware.util.c.a(this.pitch), 0, this._sendData, 5, 4);
        System.arraycopy(dji.midware.util.c.a(this.yaw), 0, this._sendData, 9, 4);
        System.arraycopy(dji.midware.util.c.a(this.throttle), 0, this._sendData, 13, 4);
    }
}
