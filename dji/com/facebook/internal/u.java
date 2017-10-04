package com.facebook.internal;

public class u {
    private static volatile String a;

    public static void a(String str) {
        a = str;
    }

    public static String a() {
        return a;
    }
}
