package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;

public class DataUpgradeSelfRequest extends n implements b {
    private static DataUpgradeSelfRequest a = null;
    private ControlCmd b;

    public enum ControlCmd {
        UNDO(0),
        DO(1),
        OTHER(7);
        
        private int d;

        private ControlCmd(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static ControlCmd find(int i) {
            ControlCmd controlCmd = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return controlCmd;
        }
    }

    public static synchronized DataUpgradeSelfRequest getInstance() {
        DataUpgradeSelfRequest dataUpgradeSelfRequest;
        synchronized (DataUpgradeSelfRequest.class) {
            if (a == null) {
                a = new DataUpgradeSelfRequest();
            }
            dataUpgradeSelfRequest = a;
        }
        return dataUpgradeSelfRequest;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public DataUpgradeSelfRequest a(ControlCmd controlCmd) {
        this.b = controlCmd;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) a.c.a();
        this._sendData[1] = (byte) this.b.a();
    }

    public void start() {
        c cVar = new c();
        if (this.pack != null) {
            cVar.f = DeviceType.APP.value();
            cVar.h = this.pack.f;
            cVar.g = this.pack.e;
            cVar.j = q.a.b.a();
            cVar.k = q.c.b.a();
            cVar.l = q.b.a.a();
            cVar.m = p.a.a();
            cVar.n = this.pack.n;
            start(cVar);
        }
    }
}
