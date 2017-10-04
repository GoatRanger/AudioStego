package com.facebook.share.internal;

import com.facebook.internal.ab;
import com.facebook.internal.h;

public enum p implements h {
    SHARE_DIALOG(ab.i),
    PHOTOS(ab.k),
    VIDEO(ab.o);
    
    private int d;

    private p(int i) {
        this.d = i;
    }

    public String a() {
        return ab.O;
    }

    public int b() {
        return this.d;
    }
}
