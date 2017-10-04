package com.google.a.g.a.a.a;

final class m {
    private int a = 0;
    private a b = a.NUMERIC;

    private enum a {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    m() {
    }

    int a() {
        return this.a;
    }

    void a(int i) {
        this.a = i;
    }

    void b(int i) {
        this.a += i;
    }

    boolean b() {
        return this.b == a.ALPHA;
    }

    boolean c() {
        return this.b == a.NUMERIC;
    }

    boolean d() {
        return this.b == a.ISO_IEC_646;
    }

    void e() {
        this.b = a.NUMERIC;
    }

    void f() {
        this.b = a.ALPHA;
    }

    void g() {
        this.b = a.ISO_IEC_646;
    }
}
