package dji.midware.data.model.P3;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.n;
import dji.midware.e.e;
import dji.midware.util.c;
import dji.pilot.usercenter.protocol.d;
import dji.sdksharedlib.b.b;

public class DataCameraSetFocusParam extends n implements e {
    private static DataCameraSetFocusParam a = null;
    private boolean b = false;
    private ZoomMode c = ZoomMode.POSITION;
    private boolean d = true;
    private float e = 0.0f;
    private float f = 0.0f;
    private boolean g = true;
    private int h = 1;
    private boolean i = false;
    private ZoomMode j = ZoomMode.POSITION;
    private boolean k = true;
    private float l = 0.0f;
    private float m = 0.0f;
    private boolean n = true;
    private int o = 1;

    public enum ZoomMode {
        STEP(0),
        POSITION(1),
        CONTINUOUS(2);
        
        private int d;

        private ZoomMode(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static ZoomMode find(int i) {
            ZoomMode zoomMode = POSITION;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return zoomMode;
        }
    }

    public static synchronized DataCameraSetFocusParam getInstance() {
        DataCameraSetFocusParam dataCameraSetFocusParam;
        synchronized (DataCameraSetFocusParam.class) {
            if (a == null) {
                a = new DataCameraSetFocusParam();
            }
            dataCameraSetFocusParam = a;
        }
        return dataCameraSetFocusParam;
    }

    public DataCameraSetFocusParam a(boolean z) {
        this.b = z;
        return this;
    }

    public DataCameraSetFocusParam a(ZoomMode zoomMode) {
        this.c = zoomMode;
        return this;
    }

    public DataCameraSetFocusParam b(boolean z) {
        this.d = z;
        return this;
    }

    public DataCameraSetFocusParam a(float f) {
        this.e = f;
        return this;
    }

    public DataCameraSetFocusParam b(float f) {
        this.f = f;
        return this;
    }

    public DataCameraSetFocusParam c(boolean z) {
        this.g = z;
        return this;
    }

    public DataCameraSetFocusParam a(int i) {
        this.h = i;
        return this;
    }

    public DataCameraSetFocusParam d(boolean z) {
        this.i = z;
        return this;
    }

    public DataCameraSetFocusParam b(ZoomMode zoomMode) {
        this.j = zoomMode;
        return this;
    }

    public DataCameraSetFocusParam e(boolean z) {
        this.k = z;
        return this;
    }

    public DataCameraSetFocusParam c(float f) {
        this.l = f;
        return this;
    }

    public DataCameraSetFocusParam d(float f) {
        this.m = f;
        return this;
    }

    public DataCameraSetFocusParam f(boolean z) {
        this.n = z;
        return this;
    }

    public DataCameraSetFocusParam b(int i) {
        this.o = i;
        return this;
    }

    public float a() {
        return (((float) ((Integer) get(0, 2, Integer.class)).intValue()) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
    }

    public float b() {
        return (((float) ((Integer) get(2, 2, Integer.class)).intValue()) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
    }

    protected void doPack() {
        byte[] bArr;
        this._sendData = new byte[5];
        if (this.b) {
            byte[] bArr2 = this._sendData;
            bArr2[0] = (byte) (bArr2[0] | 128);
            bArr2 = this._sendData;
            bArr2[0] = (byte) (bArr2[0] | ((byte) ((this.c.a() << 4) & 255)));
            if (this.c == ZoomMode.STEP) {
                this._sendData[1] = (byte) ((int) (this.e * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
                bArr = this._sendData;
                bArr[2] = (byte) ((this.d ? 1 : 0) | bArr[2]);
            } else if (this.c == ZoomMode.POSITION) {
                System.arraycopy(c.b((short) ((int) (this.f * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity))), 0, this._sendData, 1, 2);
            } else if (this.c == ZoomMode.CONTINUOUS) {
                this._sendData[1] = (byte) this.h;
                bArr = this._sendData;
                bArr[2] = (byte) ((this.g ? 1 : 0) | bArr[2]);
            }
        }
        if (this.i) {
            bArr2 = this._sendData;
            bArr2[0] = (byte) (bArr2[0] | 8);
            bArr2 = this._sendData;
            bArr2[0] = (byte) (bArr2[0] | ((byte) (this.j.a() & 255)));
            if (this.j == ZoomMode.STEP) {
                int i;
                this._sendData[3] = (byte) ((int) (this.l * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
                bArr = this._sendData;
                byte b = bArr[4];
                if (this.k) {
                    i = 1;
                } else {
                    i = 0;
                }
                bArr[4] = (byte) (i | b);
            } else if (this.j == ZoomMode.POSITION) {
                System.arraycopy(c.b((short) ((int) (this.m * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity))), 0, this._sendData, 3, 2);
            } else if (this.j == ZoomMode.CONTINUOUS) {
                this._sendData[3] = (byte) this.o;
                bArr = this._sendData;
                bArr[4] = (byte) ((this.n ? 1 : 0) | bArr[4]);
            }
        }
        DJILogHelper.getInstance().LOGD(b.a, "Focus Param[" + c.i(this._sendData) + d.H, false, true);
    }

    public void start(dji.midware.e.d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = q.b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.Q.a();
        start(cVar, dVar);
    }

    public void c() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = q.b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.Q.a();
        super.start(cVar);
    }
}
