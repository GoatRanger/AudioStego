package dji.pilot.fpv.activity;

import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.m;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.pilot.flyforbid.a$b;
import dji.pilot.fpv.control.b$a;
import dji.pilot.playback.litchi.DJIPlayBackActivity$a;
import dji.setting.ui.flyc.SdModeView.a;

/* synthetic */ class DJIPreviewActivityLitchi$32 {
    static final /* synthetic */ int[] a = new int[ProductType.values().length];
    static final /* synthetic */ int[] b = new int[DJIPlayBackActivity$a.values().length];
    static final /* synthetic */ int[] c = new int[m.values().length];
    static final /* synthetic */ int[] d = new int[o.values().length];
    static final /* synthetic */ int[] e = new int[b$a.values().length];
    static final /* synthetic */ int[] f = new int[a.values().length];
    static final /* synthetic */ int[] g = new int[DJICustomType.values().length];
    static final /* synthetic */ int[] h = new int[a$b.values().length];

    static {
        try {
            h[a$b.CLEAR_MAP_OUTER_MARKER.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            h[a$b.REFRESH_MAP_OUTER_MARKER.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            h[a$b.DIMISS_FORBID_DLG.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            h[a$b.SHOW_FORBID_DLG.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            h[a$b.REFRESH_MAP_MARKER.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            h[a$b.SHOW_STRONG_WARNING_CHECK_DLG.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            h[a$b.SHOW_TAKEOFF_FORBID_DLG.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            g[DJICustomType.d.ordinal()] = 1;
        } catch (NoSuchFieldError e8) {
        }
        try {
            g[DJICustomType.a.ordinal()] = 2;
        } catch (NoSuchFieldError e9) {
        }
        try {
            g[DJICustomType.f.ordinal()] = 3;
        } catch (NoSuchFieldError e10) {
        }
        try {
            g[DJICustomType.b.ordinal()] = 4;
        } catch (NoSuchFieldError e11) {
        }
        try {
            g[DJICustomType.c.ordinal()] = 5;
        } catch (NoSuchFieldError e12) {
        }
        try {
            g[DJICustomType.g.ordinal()] = 6;
        } catch (NoSuchFieldError e13) {
        }
        try {
            g[DJICustomType.r.ordinal()] = 7;
        } catch (NoSuchFieldError e14) {
        }
        try {
            g[DJICustomType.s.ordinal()] = 8;
        } catch (NoSuchFieldError e15) {
        }
        try {
            g[DJICustomType.t.ordinal()] = 9;
        } catch (NoSuchFieldError e16) {
        }
        try {
            g[DJICustomType.h.ordinal()] = 10;
        } catch (NoSuchFieldError e17) {
        }
        try {
            g[DJICustomType.i.ordinal()] = 11;
        } catch (NoSuchFieldError e18) {
        }
        try {
            g[DJICustomType.j.ordinal()] = 12;
        } catch (NoSuchFieldError e19) {
        }
        try {
            f[a.SUCCESS.ordinal()] = 1;
        } catch (NoSuchFieldError e20) {
        }
        try {
            f[a.TIMEOUT.ordinal()] = 2;
        } catch (NoSuchFieldError e21) {
        }
        try {
            e[b$a.POINT.ordinal()] = 1;
        } catch (NoSuchFieldError e22) {
        }
        try {
            e[b$a.CENTER.ordinal()] = 2;
        } catch (NoSuchFieldError e23) {
        }
        try {
            d[o.b.ordinal()] = 1;
        } catch (NoSuchFieldError e24) {
        }
        try {
            d[o.a.ordinal()] = 2;
        } catch (NoSuchFieldError e25) {
        }
        try {
            c[m.b.ordinal()] = 1;
        } catch (NoSuchFieldError e26) {
        }
        try {
            c[m.a.ordinal()] = 2;
        } catch (NoSuchFieldError e27) {
        }
        try {
            b[DJIPlayBackActivity$a.OUTER.ordinal()] = 1;
        } catch (NoSuchFieldError e28) {
        }
        try {
            a[ProductType.litchiC.ordinal()] = 1;
        } catch (NoSuchFieldError e29) {
        }
        try {
            a[ProductType.litchiS.ordinal()] = 2;
        } catch (NoSuchFieldError e30) {
        }
        try {
            a[ProductType.P34K.ordinal()] = 3;
        } catch (NoSuchFieldError e31) {
        }
        try {
            a[ProductType.KumquatX.ordinal()] = 4;
        } catch (NoSuchFieldError e32) {
        }
        try {
            a[ProductType.KumquatS.ordinal()] = 5;
        } catch (NoSuchFieldError e33) {
        }
        try {
            a[ProductType.Orange2.ordinal()] = 6;
        } catch (NoSuchFieldError e34) {
        }
        try {
            a[ProductType.Pomato.ordinal()] = 7;
        } catch (NoSuchFieldError e35) {
        }
        try {
            a[ProductType.litchiX.ordinal()] = 8;
        } catch (NoSuchFieldError e36) {
        }
        try {
            a[ProductType.Tomato.ordinal()] = 9;
        } catch (NoSuchFieldError e37) {
        }
    }
}
