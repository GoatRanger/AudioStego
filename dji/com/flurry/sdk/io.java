package com.flurry.sdk;

import android.os.Build.VERSION;

public final class io {
    private final Class<? extends iq> a;
    private final int b;

    public io(Class<? extends iq> cls, int i) {
        this.a = cls;
        this.b = i;
    }

    public Class<? extends iq> a() {
        return this.a;
    }

    public boolean b() {
        return this.a != null && VERSION.SDK_INT >= this.b;
    }
}
