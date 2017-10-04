package dji.midware.data.config.P3;

import dji.log.DJILog;
import dji.midware.e.a;

public enum p {
    COMMON(0, new d()),
    SPECIAL(1, new n()),
    CAMERA(2, new b()),
    FLYC(3, new g()),
    GIMBAL(4, new h()),
    CENTER(5, new c()),
    RC(6, new k()),
    WIFI(7, new o()),
    DM368(8, new e()),
    OSD(9, new i()),
    EYE(10, new f()),
    SIMULATOR(11, new l()),
    BATTERY(12),
    SMARTBATTERY(13, new m()),
    RTK(15, new j()),
    OTHER(100);
    
    private static final String q = "CmdSet";
    private static p[] t;
    private int r;
    private a s;

    static {
        t = values();
    }

    private p(int i) {
        this.r = i;
    }

    private p(int i, a aVar) {
        this.r = i;
        this.s = a(aVar);
        if (this.s == null) {
            this.s = aVar;
        }
    }

    public int a() {
        return this.r;
    }

    public a b() {
        return this.s;
    }

    public boolean a(int i) {
        return this.r == i;
    }

    public static p find(int i) {
        p pVar = OTHER;
        for (int i2 = 0; i2 < t.length; i2++) {
            if (t[i2].a(i)) {
                return t[i2];
            }
        }
        return pVar;
    }

    private a a(a aVar) {
        if (aVar == null) {
            return null;
        }
        a aVar2;
        String simpleName = aVar.getClass().getSimpleName();
        try {
            Class cls = Class.forName("com.dji.midware.extension.config." + simpleName + "Extra");
            if (cls != null) {
                aVar2 = (a) cls.newInstance();
                return aVar2;
            }
        } catch (Exception e) {
            DJILog.d(q, "No exist extra class : " + simpleName);
        }
        aVar2 = null;
        return aVar2;
    }
}
