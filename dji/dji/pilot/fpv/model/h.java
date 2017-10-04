package dji.pilot.fpv.model;

import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcGetPushParams;

public class h {
    public static final int q = 45;
    public static final int r = 21;
    public static final int s = 11;
    public static final int t = 13;
    public static final int u = 1;
    public static final int v = 18;
    protected static final int w = 109;
    public DataOsdGetPushCommon a;
    public DataOsdGetPushHome b;
    public DataGimbalGetPushParams c;
    public DataRcGetPushParams d;
    public DataFlycGetPushDeformStatus e;
    public DataCenterGetPushBatteryCommon f;
    public DataFlycGetPushSmartBattery g;
    public String h = "";
    public String i = "";
    public DataRcGetPushGpsInfo j;
    public d k;
    public j l;
    public c m;
    public e n;
    public l o;
    public g p;

    public enum a {
        OSD(1),
        HOME(2),
        GIMBAL(3),
        RC(4),
        CUSTOM(5),
        DEFORM(6),
        CENTER_BATTERY(7),
        SMART_BATTERY(8),
        APP_TIP(9),
        APP_WARN(10),
        RC_GPS(11),
        RC_DEBUG(12),
        RECOVER(13),
        APP_GPS(14),
        FIRMWARE(15),
        OFDM_DEBUG(16),
        VISION_GROUP(17),
        VISION_WARN(18),
        MC_PARAM(19),
        APP_OPERATION(20),
        END(255),
        OTHER(254);
        
        private int w;

        private a(int i) {
            this.w = i;
        }

        public byte a() {
            return (byte) this.w;
        }

        public boolean a(int i) {
            return this.w == i;
        }

        public static a find(int i) {
            a aVar = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return aVar;
        }
    }

    public h() {
        b();
    }

    private void b() {
        this.a = new DataOsdGetPushCommon(false);
        this.b = new DataOsdGetPushHome(false);
        this.c = new DataGimbalGetPushParams(false);
        this.d = new DataRcGetPushParams(false);
        this.e = new DataFlycGetPushDeformStatus(false);
        this.f = new DataCenterGetPushBatteryCommon(false);
        this.g = new DataFlycGetPushSmartBattery(false);
        this.j = new DataRcGetPushGpsInfo(false);
        this.k = new d(false);
        this.l = new j(false);
        this.m = new c(false);
        this.n = new e(false);
        this.o = new l(false);
        this.p = new g(false);
    }

    public void a() {
        this.k.clear();
        if (this.l != null) {
            this.l.clear();
        }
        if (this.m != null) {
            this.m.clear();
        }
        if (this.n != null) {
            this.n.clear();
        }
        this.h = "";
        this.i = "";
    }
}
