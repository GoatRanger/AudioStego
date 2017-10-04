package com.nokia.maps;

import com.here.android.mpa.common.UnintializedMapEngineException;
import com.nokia.maps.annotation.OnlineNative;

public class BaseNativeObject {
    static boolean H = false;
    static boolean I = false;
    @OnlineNative
    protected int nativeptr;

    protected BaseNativeObject() {
        t();
    }

    protected BaseNativeObject(boolean z) {
        u();
    }

    static void t() {
        if (!H) {
            throw new UnintializedMapEngineException();
        }
    }

    static void u() {
        t();
        if (!I) {
            throw new UnintializedMapEngineException();
        }
    }
}
