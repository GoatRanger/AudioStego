package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetAudio extends n implements e {
    byte a = (byte) -1;
    byte b = (byte) -1;
    byte c = (byte) -1;
    int d = -1;
    int e = -1;
    DataCameraGetAudio f;
    d g;

    public DataCameraSetAudio a(boolean z) {
        if (z) {
            this.a = (byte) 1;
        } else {
            this.a = (byte) 0;
        }
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData[0] = (byte) (((this.a << 7) | (this.b << 1)) | (this.c << 0));
        this._sendData[1] = (byte) ((this.d << 2) | this.e);
    }

    public void start(d dVar) {
        this.g = dVar;
        this.f = new DataCameraGetAudio();
        this.f.start(new d(this) {
            final /* synthetic */ DataCameraSetAudio a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                if (this.a.a == (byte) -1) {
                    if (this.a.f.isEnable()) {
                        this.a.a = (byte) 1;
                    } else {
                        this.a.a = (byte) 0;
                    }
                }
                if (this.a.b == (byte) -1) {
                    if (this.a.f.toSecondDataRate()) {
                        this.a.b = (byte) 1;
                    } else {
                        this.a.b = (byte) 0;
                    }
                }
                if (this.a.c == (byte) -1) {
                    if (this.a.f.toFirstDataRate()) {
                        this.a.c = (byte) 1;
                    } else {
                        this.a.c = (byte) 0;
                    }
                }
                if (this.a.d == -1) {
                    this.a.d = this.a.f.getType();
                }
                if (this.a.e == -1) {
                    this.a.e = this.a.f.getSrc();
                }
                this.a.a();
            }

            public void onFailure(a aVar) {
                if (this.a.a == (byte) -1) {
                    this.a.a = (byte) 0;
                }
                if (this.a.b == (byte) -1) {
                    this.a.b = (byte) 0;
                }
                if (this.a.c == (byte) -1) {
                    this.a.c = (byte) 0;
                }
                if (this.a.d == -1) {
                    this.a.d = 0;
                }
                if (this.a.e == -1) {
                    this.a.e = 0;
                }
                this.a.a();
            }
        });
    }

    private void a() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bf.a();
        cVar.p = getSendData();
        start(cVar, this.g);
    }
}
