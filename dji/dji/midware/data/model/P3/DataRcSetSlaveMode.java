package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import java.util.ArrayList;

public class DataRcSetSlaveMode extends n implements e {
    private static DataRcSetSlaveMode a = null;
    private ControlMode b;
    private ArrayList<SlaveCustomModel> c = new ArrayList(4);

    public enum ControlMode {
        Default(0),
        Custom(1),
        OTHER(100);
        
        private int d;

        private ControlMode(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static ControlMode find(int i) {
            ControlMode controlMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return controlMode;
        }
    }

    public enum ModeFunction {
        None(0),
        Pitch(1),
        Roll(2),
        Yaw(3),
        OTHER(100);
        
        private int f;

        private ModeFunction(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static ModeFunction find(int i) {
            ModeFunction modeFunction = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return modeFunction;
        }
    }

    public static class SlaveCustomModel {
        public int a;
        public int b;

        public SlaveCustomModel(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public SlaveCustomModel a() {
            return new SlaveCustomModel(this.a, this.b);
        }
    }

    public static synchronized DataRcSetSlaveMode getInstance() {
        DataRcSetSlaveMode dataRcSetSlaveMode;
        synchronized (DataRcSetSlaveMode.class) {
            if (a == null) {
                a = new DataRcSetSlaveMode();
            }
            dataRcSetSlaveMode = a;
        }
        return dataRcSetSlaveMode;
    }

    public DataRcSetSlaveMode a(ControlMode controlMode) {
        this.b = controlMode;
        return this;
    }

    public DataRcSetSlaveMode a(ArrayList<SlaveCustomModel> arrayList) {
        this.c = arrayList;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[5];
        this._sendData[0] = (byte) this.b.a();
        if (this.b.equals(ControlMode.Custom)) {
            for (int i = 0; i < this.c.size(); i++) {
                SlaveCustomModel slaveCustomModel = (SlaveCustomModel) this.c.get(i);
                if (slaveCustomModel != null) {
                    this._sendData[i + 1] = (byte) (slaveCustomModel.b | (slaveCustomModel.a << 7));
                }
            }
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetSlaveMode.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
