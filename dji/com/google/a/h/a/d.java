package com.google.a.h.a;

final class d {
    private static final int a = -1;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private int f = -1;

    d(int i, int i2, int i3, int i4) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    boolean a() {
        return a(this.f);
    }

    boolean a(int i) {
        return i != -1 && this.d == (i % 3) * 3;
    }

    void b() {
        this.f = ((this.e / 30) * 3) + (this.d / 3);
    }

    int c() {
        return this.c - this.b;
    }

    int d() {
        return this.b;
    }

    int e() {
        return this.c;
    }

    int f() {
        return this.d;
    }

    int g() {
        return this.e;
    }

    int h() {
        return this.f;
    }

    void b(int i) {
        this.f = i;
    }

    public String toString() {
        return this.f + "|" + this.e;
    }
}
