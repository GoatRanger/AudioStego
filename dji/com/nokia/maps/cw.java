package com.nokia.maps;

import com.here.android.mpa.streetlevel.StreetLevelIconSize;
import com.here.android.mpa.streetlevel.StreetLevelIconSize.ScalePolicy;

public class cw {
    private static k<StreetLevelIconSize, cw> i = null;
    private static am<StreetLevelIconSize, cw> j = null;
    private cq a = new cq(cw.class.getName());
    private int b;
    private int c;
    private ScalePolicy d;
    private float e;
    private float f;
    private float g;
    private float h;

    static {
        ce.a(StreetLevelIconSize.class);
    }

    public static void a(k<StreetLevelIconSize, cw> kVar, am<StreetLevelIconSize, cw> amVar) {
        i = kVar;
        j = amVar;
    }

    static StreetLevelIconSize a(cw cwVar) {
        if (cwVar != null) {
            return (StreetLevelIconSize) j.a(cwVar);
        }
        return null;
    }

    public cw(int i, int i2) {
        if (i <= 0) {
            i = 0;
        }
        this.b = i;
        if (i2 <= 0) {
            i2 = 0;
        }
        this.c = i2;
        this.e = 1.0f;
        this.f = 1.0f;
        this.g = 1.0f;
        this.h = 1.0f;
        this.d = ScalePolicy.FIXED;
    }

    public void a(int i) {
        if (i <= 0) {
            i = 0;
        }
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public void b(int i) {
        if (i <= 0) {
            i = 0;
        }
        this.c = i;
    }

    public int b() {
        return this.c;
    }

    public void a(ScalePolicy scalePolicy) {
        this.d = scalePolicy;
    }

    public ScalePolicy c() {
        return this.d;
    }

    public void a(float f) {
        this.e = f;
    }

    public float d() {
        return this.e;
    }

    public void b(float f) {
        this.f = f;
    }

    public float e() {
        return this.f;
    }

    public void c(float f) {
        this.g = f;
    }

    public float f() {
        return this.g;
    }

    public void d(float f) {
        this.h = f;
    }

    public float g() {
        return this.h;
    }

    public int hashCode() {
        return (((this.d == null ? 0 : this.d.hashCode()) + ((((((((((Float.floatToIntBits(this.h) + 31) * 31) + Float.floatToIntBits(this.g)) * 31) + this.c) * 31) + Float.floatToIntBits(this.f)) * 31) + Float.floatToIntBits(this.e)) * 31)) * 31) + this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof cw)) {
            return false;
        }
        cw cwVar = (cw) obj;
        if (Float.floatToIntBits(this.h) != Float.floatToIntBits(cwVar.h)) {
            return false;
        }
        if (Float.floatToIntBits(this.g) != Float.floatToIntBits(cwVar.g)) {
            return false;
        }
        if (this.c != cwVar.c) {
            return false;
        }
        if (Float.floatToIntBits(this.f) != Float.floatToIntBits(cwVar.f)) {
            return false;
        }
        if (Float.floatToIntBits(this.e) != Float.floatToIntBits(cwVar.e)) {
            return false;
        }
        if (this.d != cwVar.d) {
            return false;
        }
        if (this.b != cwVar.b) {
            return false;
        }
        return true;
    }
}
