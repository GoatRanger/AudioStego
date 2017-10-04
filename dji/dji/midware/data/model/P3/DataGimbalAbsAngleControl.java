package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataGimbalAbsAngleControl extends n implements e {
    private static DataGimbalAbsAngleControl instance = null;
    private boolean PitchInvalid;
    private boolean RollInvalid;
    private boolean YawInvalid;
    private boolean controlMode;
    private int overtime = 10;
    private short pitchAngle;
    private short rollAngle;
    private short yawAngle;

    public static synchronized DataGimbalAbsAngleControl getInstance() {
        DataGimbalAbsAngleControl dataGimbalAbsAngleControl;
        synchronized (DataGimbalAbsAngleControl.class) {
            if (instance == null) {
                instance = new DataGimbalAbsAngleControl();
            }
            dataGimbalAbsAngleControl = instance;
        }
        return dataGimbalAbsAngleControl;
    }

    public DataGimbalAbsAngleControl setYaw(short s) {
        this.yawAngle = s;
        return this;
    }

    public DataGimbalAbsAngleControl setRoll(short s) {
        this.rollAngle = s;
        return this;
    }

    public DataGimbalAbsAngleControl setPitch(short s) {
        this.pitchAngle = s;
        return this;
    }

    public DataGimbalAbsAngleControl setControlMode(boolean z) {
        this.controlMode = z;
        return this;
    }

    public DataGimbalAbsAngleControl setPitchInvalid(boolean z) {
        this.PitchInvalid = z;
        return this;
    }

    public DataGimbalAbsAngleControl setYawInvalid(boolean z) {
        this.YawInvalid = z;
        return this;
    }

    public DataGimbalAbsAngleControl setRollInvalid(boolean z) {
        this.RollInvalid = z;
        return this;
    }

    public DataGimbalAbsAngleControl setOvertime(int i) {
        this.overtime = i;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.AbsAngleControl.a();
        super.start(cVar, dVar);
    }

    public void start() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.AbsAngleControl.a();
        super.start(cVar);
    }

    protected void doPack() {
        this._sendData = new byte[8];
        System.arraycopy(dji.midware.util.c.b(this.yawAngle), 0, this._sendData, 0, 2);
        System.arraycopy(dji.midware.util.c.b(this.rollAngle), 0, this._sendData, 2, 2);
        System.arraycopy(dji.midware.util.c.b(this.pitchAngle), 0, this._sendData, 4, 2);
        this._sendData[6] = (byte) 0;
        if (this.controlMode) {
            this._sendData[6] = (byte) (this._sendData[6] | 1);
        }
        if (this.YawInvalid) {
            this._sendData[6] = (byte) (this._sendData[6] | 2);
        }
        if (this.RollInvalid) {
            this._sendData[6] = (byte) (this._sendData[6] | 4);
        }
        if (this.PitchInvalid) {
            this._sendData[6] = (byte) (this._sendData[6] | 8);
        }
        this._sendData[7] = (byte) this.overtime;
    }
}
