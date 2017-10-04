package com.nokia.maps;

public class as {
    static int a = 16;
    private static int b = 60;
    private static boolean c = false;
    private static boolean d = false;

    static void a(boolean z) {
        c = z;
        d = false;
    }

    static boolean a() {
        return c || d;
    }

    static void a(int i) {
        dy.a(i > 0, "FPS limit value must be greater than zero");
        b = i;
        a = 1000 / b;
    }

    static int b() {
        return b;
    }

    static void b(boolean z) {
        if (!c) {
            if (z) {
                a(30);
            } else {
                a(60);
            }
            d = z;
        }
    }
}
