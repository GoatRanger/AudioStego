package com.nokia.maps;

import com.nokia.maps.annotation.Internal;

@Internal
public class ch {
    private String a;
    private double b;

    public ch(String str) {
        this.a = str;
        this.b = ((double) System.currentTimeMillis()) / 1000.0d;
    }

    public ch() {
        this(null);
    }

    public void a(double d, boolean z) {
        ck.a().a(this.a, (((double) System.currentTimeMillis()) / 1000.0d) - this.b, d, z);
    }

    public void a(String str, double d, boolean z) {
        this.a = str;
        a(d, z);
    }
}
