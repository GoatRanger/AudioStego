package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.cv.d;

/* synthetic */ class cx {
    static final /* synthetic */ int[] a = new int[d.values().length];

    static {
        try {
            a[d.RUNNING.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[d.FINISHED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
