package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.f;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.e;
import dji.midware.util.c;
import dji.pilot.visual.a.d;

public class DataSingleVisualParam extends n implements e {
    private short a = (short) 0;
    private ParamCmdId b = ParamCmdId.OTHER;
    private boolean c = false;
    private TrackingMode d = TrackingMode.HEADLESS_FOLLOW;
    private float e = d.c;
    private float f = 0.0f;
    private float g = 0.0f;
    private float h = 0.0f;
    private int i = 0;

    public enum ParamCmdId {
        TRACK_MODE(1),
        TRACK_TERRAIN_FOLLOW(2),
        TRACK_FOLLOW_GAIN(3),
        TRACK_BACKWARD(6),
        TRACK_ASS(12),
        TRACK_CIRCLE_Z(14),
        TRACK_CIRCLE_X(15),
        TRACK_CIRCLE_Y(26),
        TRACK_GPS(31),
        TRACK_INTELLIGENT(41),
        FLY_USER_SPEED(32),
        FLY_PARALLEL_GO(33),
        FLY_YAW_RESPONE(34),
        OTHER(255);
        
        private int o;

        private ParamCmdId(int i) {
            this.o = i;
        }

        public int a() {
            return this.o;
        }

        public boolean a(int i) {
            return this.o == i;
        }

        public static ParamCmdId find(int i) {
            ParamCmdId paramCmdId = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return paramCmdId;
        }
    }

    public enum TrackingMode {
        HEADLESS_FOLLOW(0),
        PARALLEL_FOLLOW(1),
        FIXED_ANGLE(2),
        WATCH_TARGET(3),
        HEAD_LOCK(4),
        WAYPOINT(5),
        OTHER(255);
        
        private int h;

        private TrackingMode(int i) {
            this.h = i;
        }

        public int a() {
            return this.h;
        }

        public boolean a(int i) {
            return this.h == i;
        }

        public static TrackingMode find(int i) {
            TrackingMode trackingMode = HEADLESS_FOLLOW;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return trackingMode;
        }
    }

    public DataSingleVisualParam a(boolean z) {
        this.c = z;
        return this;
    }

    public DataSingleVisualParam a(ParamCmdId paramCmdId) {
        this.b = paramCmdId;
        return this;
    }

    public DataSingleVisualParam a(TrackingMode trackingMode) {
        this.d = trackingMode;
        return this;
    }

    public TrackingMode a() {
        TrackingMode trackingMode = TrackingMode.HEADLESS_FOLLOW;
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.TRACK_MODE) {
            return trackingMode;
        }
        return TrackingMode.find(((Integer) get(2, 1, Integer.class)).intValue());
    }

    public DataSingleVisualParam a(float f) {
        this.e = f;
        return this;
    }

    public float b() {
        return (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.TRACK_FOLLOW_GAIN) ? d.c : ((Float) get(2, 4, Float.class)).floatValue();
    }

    public DataSingleVisualParam a(int i) {
        this.i = i;
        return this;
    }

    public int c() {
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.TRACK_TERRAIN_FOLLOW) {
            return 1;
        }
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public DataSingleVisualParam b(float f) {
        this.f = f;
        return this;
    }

    public float d() {
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.TRACK_BACKWARD) {
            return 0.0f;
        }
        return ((Float) get(2, 4, Float.class)).floatValue();
    }

    public DataSingleVisualParam b(boolean z) {
        this.i = z ? 1 : 0;
        return this;
    }

    public boolean e() {
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.TRACK_ASS) {
            return false;
        }
        boolean z;
        if (((Integer) get(2, 1, Integer.class)).intValue() != 0) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public DataSingleVisualParam c(float f) {
        this.g = f;
        return this;
    }

    public float f() {
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.TRACK_CIRCLE_Y) {
            return 0.0f;
        }
        return ((Float) get(2, 4, Float.class)).floatValue();
    }

    public DataSingleVisualParam c(boolean z) {
        this.i = z ? 1 : 0;
        return this;
    }

    public boolean g() {
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.TRACK_INTELLIGENT) {
            return false;
        }
        boolean z;
        if (((Integer) get(2, 1, Integer.class)).intValue() != 0) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public DataSingleVisualParam d(boolean z) {
        this.i = z ? 1 : 0;
        return this;
    }

    public boolean h() {
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.TRACK_GPS) {
            return false;
        }
        boolean z;
        if (((Integer) get(2, 1, Integer.class)).intValue() != 0) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public DataSingleVisualParam d(float f) {
        this.h = f;
        return this;
    }

    public float i() {
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.FLY_USER_SPEED) {
            return 3.0f;
        }
        return ((Float) get(2, 4, Float.class)).floatValue();
    }

    public DataSingleVisualParam b(int i) {
        this.i = i;
        return this;
    }

    public int j() {
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.FLY_PARALLEL_GO) {
            return 0;
        }
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public DataSingleVisualParam c(int i) {
        this.i = i;
        return this;
    }

    public int k() {
        if (this._recData == null || this._recData.length <= 0 || ParamCmdId.find(((Integer) get(0, 1, Integer.class)).intValue()) != ParamCmdId.FLY_YAW_RESPONE) {
            return 0;
        }
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    protected void doPack() {
        int i = 4;
        if (this.c) {
            this._sendData = new byte[1];
            this._sendData[0] = (byte) this.b.a();
            return;
        }
        if (ParamCmdId.TRACK_MODE == this.b) {
            this.a = (short) 3;
            this._sendData = new byte[this.a];
            this._sendData[2] = (byte) this.d.a();
            i = 1;
        } else if (ParamCmdId.TRACK_TERRAIN_FOLLOW == this.b) {
            this.a = (short) 3;
            this._sendData = new byte[this.a];
            this._sendData[2] = (byte) this.i;
            i = 1;
        } else if (ParamCmdId.TRACK_FOLLOW_GAIN == this.b) {
            this.a = (short) 6;
            this._sendData = new byte[this.a];
            System.arraycopy(c.a(this.e), 0, this._sendData, 2, 4);
        } else if (ParamCmdId.TRACK_BACKWARD == this.b) {
            this.a = (short) 6;
            this._sendData = new byte[this.a];
            System.arraycopy(c.a(this.f), 0, this._sendData, 2, 4);
        } else if (ParamCmdId.TRACK_ASS == this.b) {
            this.a = (short) 3;
            this._sendData = new byte[this.a];
            this._sendData[2] = (byte) this.i;
            i = 1;
        } else if (ParamCmdId.TRACK_CIRCLE_Y == this.b) {
            this.a = (short) 6;
            this._sendData = new byte[this.a];
            System.arraycopy(c.a(this.g), 0, this._sendData, 2, 4);
        } else if (ParamCmdId.TRACK_INTELLIGENT == this.b) {
            this.a = (short) 3;
            this._sendData = new byte[this.a];
            this._sendData[2] = (byte) this.i;
            i = 1;
        } else if (ParamCmdId.TRACK_GPS == this.b) {
            this.a = (short) 3;
            this._sendData = new byte[this.a];
            this._sendData[2] = (byte) this.i;
            i = 1;
        } else if (ParamCmdId.FLY_USER_SPEED == this.b) {
            this.a = (short) 6;
            this._sendData = new byte[this.a];
            System.arraycopy(c.a(this.h), 0, this._sendData, 2, 4);
        } else if (ParamCmdId.FLY_PARALLEL_GO == this.b) {
            this.a = (short) 3;
            this._sendData = new byte[this.a];
            this._sendData[2] = (byte) this.i;
            i = 1;
        } else if (ParamCmdId.FLY_YAW_RESPONE == this.b) {
            this.a = (short) 3;
            this._sendData = new byte[this.a];
            this._sendData[2] = (byte) this.i;
            i = 1;
        } else {
            i = 0;
        }
        if (this._sendData != null && this._sendData.length >= 2) {
            this._sendData[0] = (byte) this.b.a();
            this._sendData[1] = (byte) i;
        }
    }

    public void start(dji.midware.e.d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.SINGLE.value();
        cVar.g = 7;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.k.a();
        if (this.c) {
            cVar.n = f.a.GetParams.a();
        } else {
            cVar.n = f.a.SetParam.a();
        }
        cVar.v = 4000;
        start(cVar, dVar);
    }
}
