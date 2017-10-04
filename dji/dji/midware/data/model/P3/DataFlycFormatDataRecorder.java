package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycFormatDataRecorder extends n implements e {
    private static DataFlycFormatDataRecorder instance = null;
    private FORMAT_ACTION mFormatAction = FORMAT_ACTION.FORMAT;

    public enum FORMAT_ACTION {
        UNDEFINE(0),
        FORMAT(1),
        DELOLDFILES(2),
        TEST(3),
        OTHER(100);
        
        private int data;

        private FORMAT_ACTION(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FORMAT_ACTION find(int i) {
            FORMAT_ACTION format_action = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return format_action;
        }
    }

    public static synchronized DataFlycFormatDataRecorder getInstance() {
        DataFlycFormatDataRecorder dataFlycFormatDataRecorder;
        synchronized (DataFlycFormatDataRecorder.class) {
            if (instance == null) {
                instance = new DataFlycFormatDataRecorder();
            }
            dataFlycFormatDataRecorder = instance;
        }
        return dataFlycFormatDataRecorder;
    }

    public DataFlycFormatDataRecorder setAction(FORMAT_ACTION format_action) {
        this.mFormatAction = format_action;
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
        cVar.n = g.a.y.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.mFormatAction.value();
    }
}
