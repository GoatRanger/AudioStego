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

public class DataFlycFaultInject extends n implements e {
    private static DataFlycFaultInject a = null;
    private long b = 1;
    private int c = 32;
    private INJECT_CMD d = INJECT_CMD.FIT_CMD_STOP;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private float j = 0.0f;
    private float k = 0.0f;
    private float l = 0.0f;
    private float m = 0.0f;
    private long n = 0;

    public enum INJECT_CMD {
        FIT_CMD_STOP(1),
        FIT_CMD_OPEN(2),
        FIT_CMD_SEND(3);
        
        private int d;

        private INJECT_CMD(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static INJECT_CMD find(int i) {
            INJECT_CMD inject_cmd = FIT_CMD_STOP;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return inject_cmd;
        }
    }

    public static DataFlycFaultInject getInstance() {
        if (a == null) {
            a = new DataFlycFaultInject();
        }
        return a;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataFlycFaultInject a(INJECT_CMD inject_cmd) {
        this.d = inject_cmd;
        return this;
    }

    public DataFlycFaultInject a(int i) {
        this.e = i;
        return this;
    }

    public DataFlycFaultInject b(int i) {
        this.f = i;
        return this;
    }

    public DataFlycFaultInject c(int i) {
        this.g = i;
        return this;
    }

    public DataFlycFaultInject d(int i) {
        this.h = i;
        return this;
    }

    public DataFlycFaultInject e(int i) {
        this.i = i;
        return this;
    }

    public DataFlycFaultInject a(float f) {
        this.j = f;
        return this;
    }

    public DataFlycFaultInject b(float f) {
        this.k = f;
        return this;
    }

    public DataFlycFaultInject c(float f) {
        this.l = f;
        return this;
    }

    public DataFlycFaultInject d(float f) {
        this.m = f;
        return this;
    }

    public DataFlycFaultInject a(long j) {
        this.n = j;
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
        cVar.n = g.a.aF.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[32];
        dji.midware.util.c.a(dji.midware.util.c.b(this.b), this._sendData, 0);
        dji.midware.util.c.a(dji.midware.util.c.b(this.c), this._sendData, 4);
        this._sendData[6] = (byte) this.d.a();
        this._sendData[7] = (byte) this.e;
        this._sendData[8] = (byte) this.f;
        this._sendData[9] = (byte) this.g;
        this._sendData[10] = (byte) this.h;
        this._sendData[11] = (byte) this.i;
        dji.midware.util.c.a(dji.midware.util.c.a(this.j), this._sendData, 12);
        dji.midware.util.c.a(dji.midware.util.c.a(this.k), this._sendData, 16);
        dji.midware.util.c.a(dji.midware.util.c.a(this.l), this._sendData, 20);
        dji.midware.util.c.a(dji.midware.util.c.a(this.m), this._sendData, 24);
        dji.midware.util.c.a(dji.midware.util.c.b(this.n), this._sendData, 28);
    }
}
