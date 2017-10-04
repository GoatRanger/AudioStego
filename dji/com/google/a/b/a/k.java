package com.google.a.b.a;

import java.util.Map;

public final class k extends q {
    public static final String a = "KG";
    public static final String b = "LB";
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final String p;
    private final Map<String, String> q;

    public k(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, Map<String, String> map) {
        super(r.PRODUCT);
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.k = str9;
        this.l = str10;
        this.m = str11;
        this.n = str12;
        this.o = str13;
        this.p = str14;
        this.q = map;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (a(this.d, kVar.d) && a(this.e, kVar.e) && a(this.f, kVar.f) && a(this.g, kVar.g) && a(this.i, kVar.i) && a(this.j, kVar.j) && a(this.k, kVar.k) && a(this.l, kVar.l) && a(this.m, kVar.m) && a(this.n, kVar.n) && a(this.o, kVar.o) && a(this.p, kVar.p) && a(this.q, kVar.q)) {
            return true;
        }
        return false;
    }

    private static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public int hashCode() {
        return ((((((((((((0 ^ a(this.d)) ^ a(this.e)) ^ a(this.f)) ^ a(this.g)) ^ a(this.i)) ^ a(this.j)) ^ a(this.k)) ^ a(this.l)) ^ a(this.m)) ^ a(this.n)) ^ a(this.o)) ^ a(this.p)) ^ a(this.q);
    }

    private static int a(Object obj) {
        return obj == null ? 0 : obj.hashCode();
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public String h() {
        return this.j;
    }

    public String i() {
        return this.k;
    }

    public String j() {
        return this.l;
    }

    public String k() {
        return this.m;
    }

    public String l() {
        return this.n;
    }

    public String m() {
        return this.o;
    }

    public String n() {
        return this.p;
    }

    public Map<String, String> o() {
        return this.q;
    }

    public String q() {
        return String.valueOf(this.c);
    }
}
