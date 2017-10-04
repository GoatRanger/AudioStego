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
import java.util.ArrayList;

public class DataCameraControlTransCode extends n implements e {
    private static DataCameraControlTransCode a = null;
    private ControlType b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private ToResolution h;
    private ToFps i;
    private int j;
    private ArrayList<DJIFragmentModel> k;

    public enum ControlType {
        STOP_All(0),
        START(1),
        STOP_CUR(2),
        PAUSE(3),
        ADD(4),
        OTHER(100);
        
        private int g;

        private ControlType(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
        }

        public static ControlType find(int i) {
            ControlType controlType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return controlType;
        }
    }

    public class DJIFragmentModel {
        public int a;
        public int b;
        final /* synthetic */ DataCameraControlTransCode c;

        public DJIFragmentModel(DataCameraControlTransCode dataCameraControlTransCode) {
            this.c = dataCameraControlTransCode;
        }
    }

    public enum ToFps {
        fps15(0),
        fps24(1),
        fps25(2),
        fps30(3),
        fps48(4),
        fps50(5),
        fps60(6),
        fps120(7),
        fps240(8),
        fps480(9),
        OTHER(100);
        
        private int l;

        private ToFps(int i) {
            this.l = i;
        }

        public int a() {
            return this.l;
        }

        public boolean a(int i) {
            return this.l == i;
        }

        public static ToFps find(int i) {
            ToFps toFps = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return toFps;
        }
    }

    public enum ToResolution {
        Default(0),
        R1920_1280_16_9(1),
        R1280_720_16_9(2),
        R848_480_16_9(3),
        R432_240_16_9(4),
        OTHER(100);
        
        private int g;

        private ToResolution(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
        }

        public static ToResolution find(int i) {
            ToResolution toResolution = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return toResolution;
        }
    }

    public static synchronized DataCameraControlTransCode getInstance() {
        DataCameraControlTransCode dataCameraControlTransCode;
        synchronized (DataCameraControlTransCode.class) {
            if (a == null) {
                a = new DataCameraControlTransCode();
            }
            dataCameraControlTransCode = a;
        }
        return dataCameraControlTransCode;
    }

    public DataCameraControlTransCode a(ControlType controlType) {
        this.b = controlType;
        return this;
    }

    public DataCameraControlTransCode a(int i) {
        this.c = i;
        return this;
    }

    public DataCameraControlTransCode b(int i) {
        this.d = i;
        return this;
    }

    public DataCameraControlTransCode c(int i) {
        this.e = i;
        return this;
    }

    public DataCameraControlTransCode d(int i) {
        this.f = i;
        return this;
    }

    public DataCameraControlTransCode e(int i) {
        this.g = i;
        return this;
    }

    public DataCameraControlTransCode a(ToResolution toResolution) {
        this.h = toResolution;
        return this;
    }

    public DataCameraControlTransCode a(ToFps toFps) {
        this.i = toFps;
        return this;
    }

    public DataCameraControlTransCode f(int i) {
        this.j = i;
        return this;
    }

    public DataCameraControlTransCode a(ArrayList<DJIFragmentModel> arrayList) {
        this.k = arrayList;
        return this;
    }

    protected void doPack() {
        int size = this.k.size();
        this._sendData = new byte[((size * 8) + 19)];
        this._sendData[0] = (byte) this.b.a();
        Object b = c.b((short) this.c);
        System.arraycopy(b, 0, this._sendData, 1, b.length);
        int length = b.length + 1;
        b = c.b((short) this.d);
        System.arraycopy(b, 0, this._sendData, length, b.length);
        length += b.length;
        b = c.a(this.e);
        System.arraycopy(b, 0, this._sendData, length, b.length);
        length += b.length;
        b = c.b((short) this.f);
        System.arraycopy(b, 0, this._sendData, length, b.length);
        length += b.length;
        b = c.b((short) this.g);
        System.arraycopy(b, 0, this._sendData, length, b.length);
        length += b.length;
        this._sendData[length] = (byte) this.h.a();
        length++;
        this._sendData[length] = (byte) this.i.a();
        length++;
        b = c.b((short) this.j);
        System.arraycopy(b, 0, this._sendData, length, b.length);
        length += b.length;
        b = c.b((short) size);
        System.arraycopy(b, 0, this._sendData, length, b.length);
        length += b.length;
        int i = length;
        for (int i2 = 0; i2 < size; i2++) {
            DJIFragmentModel dJIFragmentModel = (DJIFragmentModel) this.k.get(i2);
            Object a = c.a(dJIFragmentModel.a);
            System.arraycopy(a, 0, this._sendData, i, a.length);
            i += a.length;
            Object a2 = c.a(dJIFragmentModel.b);
            System.arraycopy(a2, 0, this._sendData, i, a2.length);
            i += a2.length;
        }
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.ba.a();
        start(cVar, dVar);
    }
}
