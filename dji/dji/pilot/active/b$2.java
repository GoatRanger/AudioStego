package dji.pilot.active;

import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;

/* synthetic */ class b$2 {
    static final /* synthetic */ int[] a = new int[p.values().length];
    static final /* synthetic */ int[] b = new int[o.values().length];

    static {
        try {
            b[o.a.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[p.a.ordinal()] = 1;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[p.b.ordinal()] = 2;
        } catch (NoSuchFieldError e3) {
        }
    }
}
