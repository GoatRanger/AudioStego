package com.nokia.maps.a;

import com.here.a.a.a.a.ac;
import com.here.a.a.a.a.i;
import com.here.a.a.a.a.t;
import com.here.android.mpa.urbanmobility.CoverageType;
import com.here.android.mpa.urbanmobility.Link;
import com.here.android.mpa.urbanmobility.Operator;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class ai {
    private static am<Operator, ai> f;
    private String a;
    private String b;
    private CoverageType c;
    private Link d;
    private Link e;

    protected ai(ac acVar) {
        this.a = (String) acVar.b.c("");
        this.b = acVar.a;
        this.c = acVar.c.c() ? n.a((i) acVar.c.b()) : CoverageType.UNKNOWN;
        if (acVar.f.c()) {
            this.d = z.a(new z((t) acVar.f.b()));
        }
        if (acVar.g.c()) {
            this.e = z.a(new z((t) acVar.g.b()));
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public CoverageType c() {
        return this.c;
    }

    public Link d() {
        return this.d;
    }

    public Link e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ai aiVar = (ai) obj;
        if (this.a.equals(aiVar.a) && this.b.equals(aiVar.b) && this.c == aiVar.c) {
            if (this.d != null) {
                if (this.d.equals(aiVar.d)) {
                    return true;
                }
            } else if (aiVar.d == null) {
                if (this.e != null) {
                    if (this.e.equals(aiVar.e)) {
                        return true;
                    }
                } else if (aiVar.e == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31;
        if (this.d != null) {
            hashCode = this.d.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.e != null) {
            i = this.e.hashCode();
        }
        return hashCode + i;
    }

    public static void a(am<Operator, ai> amVar) {
        f = amVar;
    }

    static Operator a(ai aiVar) {
        if (aiVar != null) {
            return (Operator) f.a(aiVar);
        }
        return null;
    }

    static {
        ce.a(Operator.class);
    }
}
