package com.nokia.maps;

import com.nokia.maps.annotation.InternalNative;

public final class ci {
    @InternalNative
    public String a;
    @InternalNative
    public long b;
    @InternalNative
    public long c;
    @InternalNative
    public double d;
    @InternalNative
    public double e;
    @InternalNative
    public double f;
    @InternalNative
    public double g;
    @InternalNative
    public double h;
    @InternalNative
    public double i;
    @InternalNative
    public double j;
    @InternalNative
    public double k;

    @InternalNative
    private ci() {
    }

    public ci(String str, double d, double d2, boolean z) {
        long j = 1;
        this.a = str;
        this.d = d;
        this.e = d;
        this.f = d;
        this.g = d;
        this.b = 1;
        if (z) {
            j = 0;
        }
        this.c = j;
        this.h = d2;
        this.i = d2;
        this.j = d2;
        this.k = d2;
    }

    void a(double d, double d2, boolean z) {
        synchronized (this) {
            if (!z) {
                this.c++;
            }
            this.b++;
            this.d += d;
            this.h += d2;
            if (d < this.e) {
                this.e = d;
            }
            if (d > this.f) {
                this.f = d;
            }
            if (d2 < this.i) {
                this.i = d2;
            }
            if (d2 > this.j) {
                this.j = d2;
            }
            this.g = this.d / ((double) this.b);
            this.k = this.h / ((double) this.b);
        }
    }
}
