package com.nokia.maps.a;

import com.here.a.a.a.a.j;
import com.here.android.mpa.urbanmobility.DepartureFrequency;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class q {
    private static am<DepartureFrequency, q> e;
    private int a;
    private int b;
    private int c;
    private int d;

    protected q(j jVar) {
        this.a = ((Integer) jVar.a.c(Integer.valueOf(-1))).intValue();
        this.b = ((Integer) jVar.c.c(Integer.valueOf(-1))).intValue();
        this.c = ((Integer) jVar.b.c(Integer.valueOf(-1))).intValue();
        this.d = ((Integer) jVar.d.c(Integer.valueOf(-1))).intValue();
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        q qVar = (q) obj;
        if (this.a == qVar.a && this.b == qVar.b && this.c == qVar.c && this.d == qVar.d) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d;
    }

    public static void a(am<DepartureFrequency, q> amVar) {
        e = amVar;
    }

    static DepartureFrequency a(q qVar) {
        if (qVar != null) {
            return (DepartureFrequency) e.a(qVar);
        }
        return null;
    }

    static {
        ce.a(DepartureFrequency.class);
    }
}
