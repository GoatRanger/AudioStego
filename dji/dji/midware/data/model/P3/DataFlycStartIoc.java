package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
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

public class DataFlycStartIoc extends n implements e {
    private static DataFlycStartIoc instance = null;
    private IOCType mIocType;

    public enum IOCType {
        IOCTypeCourseLock(1),
        IOCTypeHomeLock(2),
        IOCTripod(3),
        IOCTypeHomeLockA2(4),
        IOCTypeOther(100);
        
        private int data;

        private IOCType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static IOCType find(int i) {
            IOCType iOCType = IOCTypeOther;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return iOCType;
        }
    }

    public static synchronized DataFlycStartIoc getInstance() {
        DataFlycStartIoc dataFlycStartIoc;
        synchronized (DataFlycStartIoc.class) {
            if (instance == null) {
                instance = new DataFlycStartIoc();
            }
            dataFlycStartIoc = instance;
        }
        return dataFlycStartIoc;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataFlycStartIoc setMode(IOCType iOCType) {
        this.mIocType = iOCType;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.mIocType.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.ay.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        start(cVar, dVar);
    }
}
