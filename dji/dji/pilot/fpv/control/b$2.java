package dji.pilot.fpv.control;

import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;

/* synthetic */ class b$2 {
    static final /* synthetic */ int[] a = new int[TYPE.values().length];
    static final /* synthetic */ int[] b = new int[MODE.values().length];
    static final /* synthetic */ int[] c = new int[SDCardState.values().length];
    static final /* synthetic */ int[] d = new int[o.values().length];

    static {
        try {
            d[o.b.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            d[o.a.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            c[SDCardState.Full.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            c[SDCardState.Normal.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
        try {
            c[SDCardState.ToFormat.ordinal()] = 3;
        } catch (NoSuchFieldError e5) {
        }
        try {
            c[SDCardState.Slow.ordinal()] = 4;
        } catch (NoSuchFieldError e6) {
        }
        try {
            b[MODE.TAKEPHOTO.ordinal()] = 1;
        } catch (NoSuchFieldError e7) {
        }
        try {
            b[MODE.RECORD.ordinal()] = 2;
        } catch (NoSuchFieldError e8) {
        }
        try {
            b[MODE.PLAYBACK.ordinal()] = 3;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[TYPE.b.ordinal()] = 1;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[TYPE.c.ordinal()] = 2;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[TYPE.d.ordinal()] = 3;
        } catch (NoSuchFieldError e12) {
        }
        try {
            a[TYPE.g.ordinal()] = 4;
        } catch (NoSuchFieldError e13) {
        }
        try {
            a[TYPE.e.ordinal()] = 5;
        } catch (NoSuchFieldError e14) {
        }
        try {
            a[TYPE.f.ordinal()] = 6;
        } catch (NoSuchFieldError e15) {
        }
    }
}
