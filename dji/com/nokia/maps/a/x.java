package com.nokia.maps.a;

import com.here.a.a.a.a.ac;
import com.here.a.a.a.a.s;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.LineCategory;
import com.here.android.mpa.urbanmobility.LineStyle;
import com.here.android.mpa.urbanmobility.Operator;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class x {
    private static am<Line, x> h;
    private String a;
    private String b;
    private LineCategory c;
    private LineStyle d;
    private Operator e;
    private boolean f;
    private boolean g;

    protected x(s sVar) {
        this.a = sVar.a;
        this.b = (String) sVar.c.c("");
        this.c = w.a(new w(sVar));
        this.d = y.a(new y(sVar));
        if (sVar.d.c()) {
            this.e = ai.a(new ai((ac) sVar.d.b()));
        }
        this.f = ((Boolean) sVar.g.c(Boolean.valueOf(false))).booleanValue();
        this.g = ((Boolean) sVar.f.c(Boolean.valueOf(false))).booleanValue();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public LineCategory c() {
        return this.c;
    }

    public LineStyle d() {
        return this.d;
    }

    public Operator e() {
        return this.e;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        x xVar = (x) obj;
        if (this.a.equals(xVar.a) && this.b.equals(xVar.b) && this.c.equals(xVar.c) && this.d.equals(xVar.d)) {
            if (this.e != null) {
                if (this.e.equals(xVar.e)) {
                    return true;
                }
            } else if (xVar.e == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.e != null ? this.e.hashCode() : 0) + (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31);
    }

    public static void a(am<Line, x> amVar) {
        h = amVar;
    }

    static Line a(x xVar) {
        if (xVar != null) {
            return (Line) h.a(xVar);
        }
        return null;
    }

    static {
        ce.a(Line.class);
    }
}
