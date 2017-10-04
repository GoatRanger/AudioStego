package com.facebook.share.internal;

import com.facebook.internal.ab;
import com.facebook.internal.h;

public enum f implements h {
    LIKE_DIALOG(ab.m);
    
    private int b;

    private f(int i) {
        this.b = i;
    }

    public String a() {
        return ab.S;
    }

    public int b() {
        return this.b;
    }
}
