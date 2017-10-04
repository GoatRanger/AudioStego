package com.nokia.maps;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
class d {
    private static int[] b;
    private static int[] c;
    private static int[] d;
    private static int e;
    private static int f;
    private static int g;
    private static int h;
    private static int i;
    private static int j;
    ARLayoutControl a = null;

    d(ARLayoutControl aRLayoutControl) {
        this.a = aRLayoutControl;
    }

    void a(int i, int i2, int i3) {
        ARSensors f = this.a.f();
        if (f != null) {
            e = f.b(0.19f);
            f = f.c(0.19f);
            g = f.b(0.19f);
            h = f.c(0.19f);
            i = f.b(0.19f);
            j = f.c(0.19f);
            byte[] a = bc.a(i);
            if (e > 0 && f > 0 && a != null) {
                c = bc.a(a, g, h);
            }
            a = bc.a(i2);
            if (i > 0 && j > 0 && a != null) {
                d = bc.a(a, i, j);
            }
            a = bc.a(i3);
            if (g > 0 && h > 0 && a != null) {
                b = bc.a(a, e, f);
            }
        }
    }

    static int[] a() {
        return c;
    }

    static int[] b() {
        return b;
    }

    static int[] c() {
        return d;
    }

    static int d() {
        return g;
    }

    static int e() {
        return h;
    }

    static int f() {
        return e;
    }

    static int g() {
        return f;
    }

    static int h() {
        return i;
    }

    static int i() {
        return j;
    }

    void j() {
        this.a = null;
    }
}
