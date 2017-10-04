package com.e;

import java.util.Map;

public class bl extends ao {
    Map<String, String> a = null;
    Map<String, String> b = null;
    String f = "";
    byte[] g = null;

    public Map<String, String> a() {
        return this.b;
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(Map<String, String> map) {
        this.a = map;
    }

    public void a(byte[] bArr) {
        this.g = bArr;
    }

    public Map<String, String> b() {
        return this.a;
    }

    public String c() {
        return this.f;
    }

    public byte[] d() {
        return this.g;
    }
}
