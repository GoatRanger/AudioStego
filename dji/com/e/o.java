package com.e;

import java.util.HashMap;
import java.util.Map;

public abstract class o {
    @h(a = "b2", b = 2)
    protected int a = -1;
    @h(a = "b1", b = 6)
    protected String b;
    @h(a = "b3", b = 2)
    protected int c = 1;
    @h(a = "a1", b = 6)
    private String d;

    public static String c(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("b2").append("=").append(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("b1", str);
        return f.a(hashMap);
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.b;
    }

    public void b(int i) {
        this.c = i;
    }

    public void b(String str) {
        this.d = dd.b(str);
    }

    public int c() {
        return this.c;
    }
}
