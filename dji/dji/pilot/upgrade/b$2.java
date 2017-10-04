package dji.pilot.upgrade;

import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.f.b;

/* synthetic */ class b$2 {
    static final /* synthetic */ int[] a = new int[b$b.values().length];
    static final /* synthetic */ int[] b = new int[b$c.values().length];
    static final /* synthetic */ int[] c = new int[ProductType.values().length];
    static final /* synthetic */ int[] d = new int[b.values().length];
    static final /* synthetic */ int[] e = new int[CameraType.values().length];

    static {
        try {
            e[CameraType.DJICameraTypeFC350.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            e[CameraType.DJICameraTypeFC550.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            e[CameraType.DJICameraTypeFC550Raw.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            e[CameraType.DJICameraTypeTau336.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            e[CameraType.DJICameraTypeTau640.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            e[CameraType.DJICameraTypeCV600.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            e[CameraType.DJICameraTypeGD600.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            d[b.a.ordinal()] = 1;
        } catch (NoSuchFieldError e8) {
        }
        try {
            d[b.d.ordinal()] = 2;
        } catch (NoSuchFieldError e9) {
        }
        try {
            c[ProductType.litchiC.ordinal()] = 1;
        } catch (NoSuchFieldError e10) {
        }
        try {
            c[ProductType.litchiS.ordinal()] = 2;
        } catch (NoSuchFieldError e11) {
        }
        try {
            c[ProductType.litchiX.ordinal()] = 3;
        } catch (NoSuchFieldError e12) {
        }
        try {
            c[ProductType.P34K.ordinal()] = 4;
        } catch (NoSuchFieldError e13) {
        }
        try {
            c[ProductType.Longan.ordinal()] = 5;
        } catch (NoSuchFieldError e14) {
        }
        try {
            c[ProductType.LonganPro.ordinal()] = 6;
        } catch (NoSuchFieldError e15) {
        }
        try {
            c[ProductType.LonganRaw.ordinal()] = 7;
        } catch (NoSuchFieldError e16) {
        }
        try {
            c[ProductType.LonganZoom.ordinal()] = 8;
        } catch (NoSuchFieldError e17) {
        }
        try {
            c[ProductType.LonganMobile.ordinal()] = 9;
        } catch (NoSuchFieldError e18) {
        }
        try {
            c[ProductType.N1.ordinal()] = 10;
        } catch (NoSuchFieldError e19) {
        }
        try {
            c[ProductType.PM820.ordinal()] = 11;
        } catch (NoSuchFieldError e20) {
        }
        try {
            c[ProductType.PM820PRO.ordinal()] = 12;
        } catch (NoSuchFieldError e21) {
        }
        try {
            c[ProductType.Tomato.ordinal()] = 13;
        } catch (NoSuchFieldError e22) {
        }
        try {
            c[ProductType.Pomato.ordinal()] = 14;
        } catch (NoSuchFieldError e23) {
        }
        try {
            c[ProductType.KumquatS.ordinal()] = 15;
        } catch (NoSuchFieldError e24) {
        }
        try {
            c[ProductType.KumquatX.ordinal()] = 16;
        } catch (NoSuchFieldError e25) {
        }
        try {
            c[ProductType.Orange2.ordinal()] = 17;
        } catch (NoSuchFieldError e26) {
        }
        try {
            c[ProductType.Orange.ordinal()] = 18;
        } catch (NoSuchFieldError e27) {
        }
        try {
            c[ProductType.BigBanana.ordinal()] = 19;
        } catch (NoSuchFieldError e28) {
        }
        try {
            c[ProductType.Olives.ordinal()] = 20;
        } catch (NoSuchFieldError e29) {
        }
        try {
            c[ProductType.OrangeRAW.ordinal()] = 21;
        } catch (NoSuchFieldError e30) {
        }
        try {
            c[ProductType.OrangeCV600.ordinal()] = 22;
        } catch (NoSuchFieldError e31) {
        }
        try {
            c[ProductType.OTHER.ordinal()] = 23;
        } catch (NoSuchFieldError e32) {
        }
        try {
            c[ProductType.Grape2.ordinal()] = 24;
        } catch (NoSuchFieldError e33) {
        }
        try {
            c[ProductType.A2.ordinal()] = 25;
        } catch (NoSuchFieldError e34) {
        }
        try {
            c[ProductType.A3.ordinal()] = 26;
        } catch (NoSuchFieldError e35) {
        }
        try {
            c[ProductType.N3.ordinal()] = 27;
        } catch (NoSuchFieldError e36) {
        }
        try {
            b[b$c.P3s.ordinal()] = 1;
        } catch (NoSuchFieldError e37) {
        }
        try {
            b[b$c.P3x.ordinal()] = 2;
        } catch (NoSuchFieldError e38) {
        }
        try {
            b[b$c.M100.ordinal()] = 3;
        } catch (NoSuchFieldError e39) {
        }
        try {
            b[b$c.Inspire.ordinal()] = 4;
        } catch (NoSuchFieldError e40) {
        }
        try {
            b[b$c.P4.ordinal()] = 5;
        } catch (NoSuchFieldError e41) {
        }
        try {
            b[b$c.POMATO.ordinal()] = 6;
        } catch (NoSuchFieldError e42) {
        }
        try {
            b[b$c.Kumquat.ordinal()] = 7;
        } catch (NoSuchFieldError e43) {
        }
        try {
            b[b$c.Other.ordinal()] = 8;
        } catch (NoSuchFieldError e44) {
        }
        try {
            a[b$b.P3c.ordinal()] = 1;
        } catch (NoSuchFieldError e45) {
        }
        try {
            a[b$b.P3s.ordinal()] = 2;
        } catch (NoSuchFieldError e46) {
        }
        try {
            a[b$b.P3x.ordinal()] = 3;
        } catch (NoSuchFieldError e47) {
        }
        try {
            a[b$b.P34k.ordinal()] = 4;
        } catch (NoSuchFieldError e48) {
        }
        try {
            a[b$b.OSMO.ordinal()] = 5;
        } catch (NoSuchFieldError e49) {
        }
        try {
            a[b$b.OSMOPRO.ordinal()] = 6;
        } catch (NoSuchFieldError e50) {
        }
        try {
            a[b$b.OSMORAW.ordinal()] = 7;
        } catch (NoSuchFieldError e51) {
        }
        try {
            a[b$b.OSMOZOOM.ordinal()] = 8;
        } catch (NoSuchFieldError e52) {
        }
        try {
            a[b$b.OSMOMOBILE.ordinal()] = 9;
        } catch (NoSuchFieldError e53) {
        }
        try {
            a[b$b.M100.ordinal()] = 10;
        } catch (NoSuchFieldError e54) {
        }
        try {
            a[b$b.Inspire.ordinal()] = 11;
        } catch (NoSuchFieldError e55) {
        }
        try {
            a[b$b.LB2.ordinal()] = 12;
        } catch (NoSuchFieldError e56) {
        }
        try {
            a[b$b.P4.ordinal()] = 13;
        } catch (NoSuchFieldError e57) {
        }
        try {
            a[b$b.A3.ordinal()] = 14;
        } catch (NoSuchFieldError e58) {
        }
        try {
            a[b$b.N3.ordinal()] = 15;
        } catch (NoSuchFieldError e59) {
        }
        try {
            a[b$b.Other.ordinal()] = 16;
        } catch (NoSuchFieldError e60) {
        }
    }
}
