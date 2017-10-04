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

public class DataCameraControlUpgrade extends n implements e {
    private static DataCameraControlUpgrade instance = null;
    private ControlCmd controlCmd;

    public enum ControlCmd {
        Cancel(0),
        Start(1),
        Pause(2),
        Stop(3),
        StopPush(4),
        Restart(5),
        OTHER(7);
        
        private int data;

        private ControlCmd(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static ControlCmd find(int i) {
            ControlCmd controlCmd = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return controlCmd;
        }
    }

    public static synchronized DataCameraControlUpgrade getInstance() {
        DataCameraControlUpgrade dataCameraControlUpgrade;
        synchronized (DataCameraControlUpgrade.class) {
            if (instance == null) {
                instance = new DataCameraControlUpgrade();
            }
            dataCameraControlUpgrade = instance;
        }
        return dataCameraControlUpgrade;
    }

    public DataCameraControlUpgrade setControlCmd(ControlCmd controlCmd) {
        this.controlCmd = controlCmd;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.controlCmd.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.u.a();
        start(cVar, dVar);
    }
}
