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

public class DataGimbalControl extends n implements e {
    private static DataGimbalControl instance = null;
    private boolean isReset;
    private MODE mode;
    private int pitch;
    private int roll;
    private int yaw;

    public enum MODE {
        YawNoFollow(0),
        FPV(1),
        YawFollow(2),
        OTHER(100);
        
        private int data;

        private MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static MODE find(int i) {
            MODE mode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return mode;
        }
    }

    public static synchronized DataGimbalControl getInstance() {
        DataGimbalControl dataGimbalControl;
        synchronized (DataGimbalControl.class) {
            if (instance == null) {
                instance = new DataGimbalControl();
            }
            dataGimbalControl = instance;
        }
        return dataGimbalControl;
    }

    public DataGimbalControl setPitch(int i) {
        this.pitch = i;
        return this;
    }

    public DataGimbalControl setRoll(int i) {
        this.roll = i;
        return this;
    }

    public DataGimbalControl setYaw(int i) {
        this.yaw = i;
        return this;
    }

    public DataGimbalControl setMode(MODE mode) {
        this.mode = mode;
        return this;
    }

    public DataGimbalControl reset(boolean z) {
        this.isReset = z;
        return this;
    }

    protected void doPack() {
        int i;
        this._sendData = new byte[8];
        System.arraycopy(c.b(this.pitch), 0, this._sendData, 0, 2);
        System.arraycopy(c.b(this.roll), 0, this._sendData, 2, 2);
        System.arraycopy(c.b(this.yaw), 0, this._sendData, 4, 2);
        int value = this.mode.value() << 6;
        if (this.isReset) {
            i = 1;
        } else {
            i = 0;
        }
        System.arraycopy(c.b((i << 5) | value), 0, this._sendData, 6, 1);
    }

    public void start() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.GIMBAL.a();
        cVar.n = h.a.a.a();
        super.start(cVar);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.GIMBAL.a();
        cVar.n = h.a.a.a();
        start(cVar, dVar);
    }
}
