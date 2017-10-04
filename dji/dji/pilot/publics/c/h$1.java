package dji.pilot.publics.c;

import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.TRIPOD_STATUS;

/* synthetic */ class h$1 {
    static final /* synthetic */ int[] a = new int[FLYC_STATE.values().length];
    static final /* synthetic */ int[] b = new int[FLIGHT_ACTION.values().length];
    static final /* synthetic */ int[] c = new int[TRIPOD_STATUS.values().length];
    static final /* synthetic */ int[] d = new int[h$a.values().length];
    static final /* synthetic */ int[] e = new int[o.values().length];

    static {
        try {
            e[o.b.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            e[o.a.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            d[h$a.RECORD.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            d[h$a.UPDATE.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
        try {
            c[TRIPOD_STATUS.FOLOING.ordinal()] = 1;
        } catch (NoSuchFieldError e5) {
        }
        try {
            c[TRIPOD_STATUS.STRETCHING.ordinal()] = 2;
        } catch (NoSuchFieldError e6) {
        }
        try {
            b[FLIGHT_ACTION.AIRPORT_AVOID_LANDING.ordinal()] = 1;
        } catch (NoSuchFieldError e7) {
        }
        try {
            b[FLIGHT_ACTION.SMART_POWER_LANDING.ordinal()] = 2;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[FLYC_STATE.Atti.ordinal()] = 1;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[FLYC_STATE.Atti_Hover.ordinal()] = 2;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[FLYC_STATE.Atti_CL.ordinal()] = 3;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[FLYC_STATE.Atti_Limited.ordinal()] = 4;
        } catch (NoSuchFieldError e12) {
        }
        try {
            a[FLYC_STATE.GPS_Atti.ordinal()] = 5;
        } catch (NoSuchFieldError e13) {
        }
        try {
            a[FLYC_STATE.GPS_Atti_Limited.ordinal()] = 6;
        } catch (NoSuchFieldError e14) {
        }
        try {
            a[FLYC_STATE.GPS_Blake.ordinal()] = 7;
        } catch (NoSuchFieldError e15) {
        }
        try {
            a[FLYC_STATE.GPS_CL.ordinal()] = 8;
        } catch (NoSuchFieldError e16) {
        }
        try {
            a[FLYC_STATE.GPS_HomeLock.ordinal()] = 9;
        } catch (NoSuchFieldError e17) {
        }
        try {
            a[FLYC_STATE.GPS_HotPoint.ordinal()] = 10;
        } catch (NoSuchFieldError e18) {
        }
        try {
            a[FLYC_STATE.AutoTakeoff.ordinal()] = 11;
        } catch (NoSuchFieldError e19) {
        }
        try {
            a[FLYC_STATE.AssitedTakeoff.ordinal()] = 12;
        } catch (NoSuchFieldError e20) {
        }
        try {
            a[FLYC_STATE.AttiLangding.ordinal()] = 13;
        } catch (NoSuchFieldError e21) {
        }
        try {
            a[FLYC_STATE.AutoLanding.ordinal()] = 14;
        } catch (NoSuchFieldError e22) {
        }
        try {
            a[FLYC_STATE.GoHome.ordinal()] = 15;
        } catch (NoSuchFieldError e23) {
        }
    }
}
