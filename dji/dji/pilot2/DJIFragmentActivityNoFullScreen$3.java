package dji.pilot2;

import com.dji.frame.c.c.a;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus.UpgradeStep;
import dji.pilot.publics.control.a$b;
import dji.pilot.publics.control.a$c;
import dji.pilot.publics.control.a.e;

/* synthetic */ class DJIFragmentActivityNoFullScreen$3 {
    static final /* synthetic */ int[] a = new int[a$b.values().length];
    static final /* synthetic */ int[] b = new int[e.values().length];
    static final /* synthetic */ int[] c = new int[a$c.values().length];
    static final /* synthetic */ int[] d = new int[a.values().length];
    static final /* synthetic */ int[] e = new int[o.values().length];
    static final /* synthetic */ int[] f = new int[p.values().length];
    static final /* synthetic */ int[] g = new int[UpgradeStep.values().length];

    static {
        try {
            g[UpgradeStep.Check.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            g[UpgradeStep.Ack.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            g[UpgradeStep.End.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f[p.b.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f[p.a.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
        try {
            e[o.b.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            e[o.a.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            d[a.a.ordinal()] = 1;
        } catch (NoSuchFieldError e8) {
        }
        try {
            d[a.b.ordinal()] = 2;
        } catch (NoSuchFieldError e9) {
        }
        try {
            c[a$c.SHOW.ordinal()] = 1;
        } catch (NoSuchFieldError e10) {
        }
        try {
            b[e.c.ordinal()] = 1;
        } catch (NoSuchFieldError e11) {
        }
        try {
            b[e.a.ordinal()] = 2;
        } catch (NoSuchFieldError e12) {
        }
        try {
            b[e.b.ordinal()] = 3;
        } catch (NoSuchFieldError e13) {
        }
        try {
            a[a$b.NEW.ordinal()] = 1;
        } catch (NoSuchFieldError e14) {
        }
        try {
            a[a$b.OLD.ordinal()] = 2;
        } catch (NoSuchFieldError e15) {
        }
        try {
            a[a$b.OLD_FORCE.ordinal()] = 3;
        } catch (NoSuchFieldError e16) {
        }
    }
}
