package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataOsdSetLED extends n implements e {
    private LEDCtlUnit a = new LEDCtlUnit(this, 1, 0, 32, 1);
    private LEDCtlUnit b = new LEDCtlUnit(this, 1, 0, 32, 1);
    private LEDCtlUnit c = new LEDCtlUnit(this, 1, 0, 32, 1);

    public class LEDCtlUnit {
        char a;
        int b;
        char c;
        char d;
        final /* synthetic */ DataOsdSetLED e;

        public LEDCtlUnit(DataOsdSetLED dataOsdSetLED, int i, int i2, int i3, int i4) {
            this.e = dataOsdSetLED;
            this.a = (char) i;
            this.b = i2;
            this.c = (char) i3;
            this.d = (char) i4;
        }

        public void a() {
            this.a = '\u0001';
            this.b = 0;
            this.c = '\u0001';
            this.d = '\u0001';
        }
    }

    public DataOsdSetLED a() {
        this.a.a();
        this.c.a();
        this.b.a();
        return this;
    }

    public DataOsdSetLED a(int i, int i2, int i3, int i4) {
        this.a.a = (char) i;
        this.a.b = i2;
        this.a.c = (char) i3;
        this.a.d = (char) i4;
        return this;
    }

    public DataOsdSetLED b(int i, int i2, int i3, int i4) {
        this.c.a = (char) i;
        this.c.b = i2;
        this.c.c = (char) i3;
        this.c.d = (char) i4;
        return this;
    }

    public DataOsdSetLED c(int i, int i2, int i3, int i4) {
        this.b.a = (char) i;
        this.b.b = i2;
        this.b.c = (char) i3;
        this.b.d = (char) i4;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[20];
        this._sendData[1] = (byte) ((this.a.a | (this.b.a << 1)) | (this.c.a << 2));
        System.arraycopy(c.a(this.a.b), 0, this._sendData, 2, 4);
        this._sendData[6] = (byte) this.a.c;
        this._sendData[7] = (byte) this.a.d;
        System.arraycopy(c.a(this.b.b), 0, this._sendData, 8, 4);
        this._sendData[12] = (byte) this.b.c;
        this._sendData[13] = (byte) this.b.d;
        System.arraycopy(c.a(this.c.b), 0, this._sendData, 14, 4);
        this._sendData[18] = (byte) this.c.c;
        this._sendData[19] = (byte) this.c.d;
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.SetLED.a();
        cVar.v = 2000;
        cVar.w = 3;
        start(cVar, dVar);
    }
}
