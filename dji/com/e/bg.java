package com.e;

import java.util.Locale;

public class bg {
    private String a;
    private int b;
    private String c;
    private long d;

    public bg(String str, long j, int i, String str2) {
        this.a = str;
        this.d = j;
        this.b = i;
        this.c = str2;
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return String.format(Locale.US, "##h=%s, n=%d, t=%d, ex=%s##", new Object[]{this.a, Integer.valueOf(this.b), Long.valueOf(this.d), this.c});
    }
}
