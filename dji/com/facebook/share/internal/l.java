package com.facebook.share.internal;

import com.facebook.internal.ab;
import com.facebook.internal.h;

public enum l implements h {
    OG_MESSAGE_DIALOG(ab.k);
    
    private int b;

    private l(int i) {
        this.b = i;
    }

    public String a() {
        return ab.R;
    }

    public int b() {
        return this.b;
    }
}
