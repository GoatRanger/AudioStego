package com.facebook.share.internal;

import com.facebook.internal.ab;
import com.facebook.internal.h;

public enum a implements h {
    APP_INVITES_DIALOG(ab.m);
    
    private int b;

    private a(int i) {
        this.b = i;
    }

    public String a() {
        return ab.T;
    }

    public int b() {
        return this.b;
    }
}
