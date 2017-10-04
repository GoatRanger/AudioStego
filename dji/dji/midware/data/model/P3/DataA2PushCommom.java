package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataA2PushCommom extends n {
    private static DataA2PushCommom a = null;

    public enum DJIA2CtrlMode {
        Manual(0),
        Atti(1),
        AttiCL(2),
        P_GPS(6),
        P_GPS_CL(7),
        P_GPS_HL(8),
        SAFE(18);
        
        private int h;

        private DJIA2CtrlMode(int i) {
            this.h = i;
        }

        public int a() {
            return this.h;
        }

        public boolean a(int i) {
            return this.h == i;
        }

        public static DJIA2CtrlMode find(int i) {
            DJIA2CtrlMode dJIA2CtrlMode = Manual;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return dJIA2CtrlMode;
        }
    }

    public static DataA2PushCommom getInstance() {
        if (a == null) {
            a = new DataA2PushCommom();
        }
        return a;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int b() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int c() {
        return ((Integer) get(2, 4, Integer.class)).intValue();
    }

    public int d() {
        return ((Integer) get(6, 4, Integer.class)).intValue();
    }

    public DJIA2CtrlMode e() {
        return DJIA2CtrlMode.find(((Integer) get(10, 1, Integer.class)).intValue());
    }

    public int f() {
        if (e() == DJIA2CtrlMode.Manual || e() == DJIA2CtrlMode.SAFE) {
            return 0;
        }
        return ((Integer) get(11, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
