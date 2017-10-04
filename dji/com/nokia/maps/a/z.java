package com.nokia.maps.a;

import com.here.a.a.a.a.t;
import com.here.android.mpa.urbanmobility.Link;
import com.here.android.mpa.urbanmobility.Link.LinkType;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class z {
    private static am<Link, z> f;
    private String a;
    private String b;
    private String c;
    private LinkType d;
    private boolean e;

    protected z(t tVar) {
        this.a = (String) tVar.d.c("");
        this.b = tVar.a;
        this.c = (String) tVar.e.c("");
        this.e = tVar.c;
        this.d = LinkType.values()[tVar.b.ordinal()];
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public LinkType d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        z zVar = (z) obj;
        if (this.e == zVar.e && this.a.equals(zVar.a) && this.b.equals(zVar.b) && this.c.equals(zVar.c) && this.d == zVar.d) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.e ? 1 : 0) + (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31);
    }

    public static void a(am<Link, z> amVar) {
        f = amVar;
    }

    static Link a(z zVar) {
        if (zVar != null) {
            return (Link) f.a(zVar);
        }
        return null;
    }

    static {
        ce.a(Link.class);
    }
}
