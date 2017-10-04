package com.google.a.g.a;

import com.google.a.t;

public final class c {
    private final int a;
    private final int[] b;
    private final t[] c;

    public c(int i, int[] iArr, int i2, int i3, int i4) {
        this.a = i;
        this.b = iArr;
        this.c = new t[]{new t((float) i2, (float) i4), new t((float) i3, (float) i4)};
    }

    public int a() {
        return this.a;
    }

    public int[] b() {
        return this.b;
    }

    public t[] c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        if (this.a == ((c) obj).a) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a;
    }
}
