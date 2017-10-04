package com.facebook.share.internal;

import com.facebook.internal.ab;

public enum h implements com.facebook.internal.h {
    MESSAGE_DIALOG(ab.k),
    PHOTOS(ab.l),
    VIDEO(ab.q);
    
    private int d;

    private h(int i) {
        this.d = i;
    }

    public String a() {
        return ab.P;
    }

    public int b() {
        return this.d;
    }
}
