package com.nokia.maps;

import com.here.android.mpa.common.ViewObject.Type;
import com.here.android.mpa.mapping.Map$Animation;
import com.here.android.mpa.mapping.Map$Projection;

/* synthetic */ class MapImpl$17 {
    static final /* synthetic */ int[] a = new int[MapImpl$f.a().length];
    static final /* synthetic */ int[] b = new int[Type.values().length];
    static final /* synthetic */ int[] c = new int[Map$Animation.values().length];
    static final /* synthetic */ int[] d = new int[Map$Projection.values().length];

    static {
        try {
            d[Map$Projection.MERCATOR.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            d[Map$Projection.GLOBE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            c[Map$Animation.BOW.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            c[Map$Animation.LINEAR.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
        try {
            c[Map$Animation.NONE.ordinal()] = 3;
        } catch (NoSuchFieldError e5) {
        }
        try {
            b[Type.USER_OBJECT.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            b[Type.PROXY_OBJECT.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            b[Type.UNKNOWN_OBJECT.ordinal()] = 3;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[MapImpl$f.LOW_RESOLUTION.ordinal()] = 1;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[MapImpl$f.HIGH_RESOLUTION.ordinal()] = 2;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[MapImpl$f.XHIGH_RESOLUTION.ordinal()] = 3;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[MapImpl$f.AUTO_RESOLUTION.ordinal()] = 4;
        } catch (NoSuchFieldError e12) {
        }
        try {
            a[MapImpl$f.CUSTOM_RESOLUTION.ordinal()] = 5;
        } catch (NoSuchFieldError e13) {
        }
    }
}
