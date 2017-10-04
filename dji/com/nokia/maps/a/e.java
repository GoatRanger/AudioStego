package com.nokia.maps.a;

import com.here.a.a.a.a.b;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.Address;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class e {
    private static am<Address, e> k;
    private GeoCoordinate a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;

    protected e(b bVar) {
        this.a = u.a(bVar.a);
        this.b = (String) bVar.b.c("");
        this.c = (String) bVar.c.c("");
        this.d = (String) bVar.d.c("");
        this.e = (String) bVar.e.c("");
        this.f = (String) bVar.f.c("");
        this.g = (String) bVar.g.c("");
        this.h = (String) bVar.h.c("");
        this.i = (String) bVar.i.c("");
        this.j = (String) bVar.j.c("");
    }

    public GeoCoordinate a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }

    public String j() {
        return this.j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        if (this.a.equals(eVar.a) && this.b.equals(eVar.b) && this.c.equals(eVar.c) && this.d.equals(eVar.d) && this.e.equals(eVar.e) && this.f.equals(eVar.f) && this.g.equals(eVar.g) && this.h.equals(eVar.h) && this.i.equals(eVar.i) && this.j.equals(eVar.j)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + this.j.hashCode();
    }

    public static void a(am<Address, e> amVar) {
        k = amVar;
    }

    static Address a(e eVar) {
        if (eVar != null) {
            return (Address) k.a(eVar);
        }
        return null;
    }

    static {
        ce.a(Address.class);
    }
}
