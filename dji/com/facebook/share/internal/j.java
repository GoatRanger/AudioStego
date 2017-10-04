package com.facebook.share.internal;

import com.facebook.internal.ab;
import com.facebook.internal.h;

public enum j implements h {
    OG_ACTION_DIALOG(ab.i);
    
    private int b;

    private j(int i) {
        this.b = i;
    }

    public String a() {
        return ab.Q;
    }

    public int b() {
        return this.b;
    }
}
