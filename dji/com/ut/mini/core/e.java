package com.ut.mini.core;

import java.util.Map;

public class e {
    public static final e a = new e();
    private boolean b = false;
    private boolean c = false;
    private String d = null;
    private Map<String, String> e = null;
    private boolean f = false;

    public static e a() {
        return a;
    }

    public synchronized void b() {
        this.f = true;
    }

    public synchronized boolean c() {
        return this.f;
    }

    public synchronized void a(Map<String, String> map) {
        this.e = map;
    }

    public synchronized Map<String, String> d() {
        return this.e;
    }

    public synchronized void a(String str) {
        this.d = str;
    }

    public synchronized String e() {
        return this.d;
    }

    public synchronized boolean f() {
        return this.b;
    }

    public synchronized void g() {
        this.b = true;
    }

    public synchronized void h() {
        this.b = false;
    }

    public synchronized boolean i() {
        return this.c;
    }

    public synchronized void a(boolean z) {
        this.c = z;
    }
}
