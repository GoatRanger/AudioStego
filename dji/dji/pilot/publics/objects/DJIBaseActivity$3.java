package dji.pilot.publics.objects;

import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus.UpgradeEndCause;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus.UpgradeStep;
import dji.pilot.publics.control.a$b;
import dji.pilot.publics.control.a.e;

/* synthetic */ class DJIBaseActivity$3 {
    static final /* synthetic */ int[] a = new int[a$b.values().length];
    static final /* synthetic */ int[] b = new int[e.values().length];
    static final /* synthetic */ int[] c = new int[o.values().length];
    static final /* synthetic */ int[] d = new int[p.values().length];
    static final /* synthetic */ int[] e = new int[UpgradeStep.values().length];
    static final /* synthetic */ int[] f = new int[UpgradeEndCause.values().length];

    static {
        try {
            f[UpgradeEndCause.Success.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f[UpgradeEndCause.FirmwareError.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f[UpgradeEndCause.UserCancel.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f[UpgradeEndCause.VersionSame.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f[UpgradeEndCause.TimeoutCancel.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f[UpgradeEndCause.MotorUp.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            e[UpgradeStep.Check.ordinal()] = 1;
        } catch (NoSuchFieldError e7) {
        }
        try {
            e[UpgradeStep.Ack.ordinal()] = 2;
        } catch (NoSuchFieldError e8) {
        }
        try {
            e[UpgradeStep.End.ordinal()] = 3;
        } catch (NoSuchFieldError e9) {
        }
        try {
            d[p.b.ordinal()] = 1;
        } catch (NoSuchFieldError e10) {
        }
        try {
            d[p.a.ordinal()] = 2;
        } catch (NoSuchFieldError e11) {
        }
        try {
            c[o.b.ordinal()] = 1;
        } catch (NoSuchFieldError e12) {
        }
        try {
            c[o.a.ordinal()] = 2;
        } catch (NoSuchFieldError e13) {
        }
        try {
            b[e.c.ordinal()] = 1;
        } catch (NoSuchFieldError e14) {
        }
        try {
            b[e.a.ordinal()] = 2;
        } catch (NoSuchFieldError e15) {
        }
        try {
            b[e.b.ordinal()] = 3;
        } catch (NoSuchFieldError e16) {
        }
        try {
            a[a$b.NEW.ordinal()] = 1;
        } catch (NoSuchFieldError e17) {
        }
        try {
            a[a$b.OLD.ordinal()] = 2;
        } catch (NoSuchFieldError e18) {
        }
        try {
            a[a$b.OLD_FORCE.ordinal()] = 3;
        } catch (NoSuchFieldError e19) {
        }
    }
}
