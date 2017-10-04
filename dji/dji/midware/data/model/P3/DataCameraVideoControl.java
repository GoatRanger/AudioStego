package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCameraVideoControl extends n implements e {
    private static DataCameraVideoControl instance = null;
    private int progress;
    private ControlType type;

    public enum ControlType {
        Stop(0),
        Start(1),
        FastForword(2),
        FastReverse(3),
        StepTo(4),
        Pause(5),
        OTHER(100);
        
        private int data;

        private ControlType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static ControlType find(int i) {
            ControlType controlType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return controlType;
        }
    }

    public static synchronized DataCameraVideoControl getInstance() {
        DataCameraVideoControl dataCameraVideoControl;
        synchronized (DataCameraVideoControl.class) {
            if (instance == null) {
                instance = new DataCameraVideoControl();
            }
            dataCameraVideoControl = instance;
        }
        return dataCameraVideoControl;
    }

    public DataCameraVideoControl setControlType(ControlType controlType) {
        this.type = controlType;
        return this;
    }

    public DataCameraVideoControl setProgress(int i) {
        this.progress = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[5];
        this._sendData[0] = (byte) this.type.value();
        c.a(c.a(this.progress), this._sendData, 1);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.aJ.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
