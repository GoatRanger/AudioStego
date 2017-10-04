package com.e;

import android.text.TextUtils;
import java.net.Proxy;
import java.util.Map;

public abstract class ao {
    int c = 20000;
    int d = 20000;
    Proxy e = null;

    public abstract Map<String, String> a();

    public final void a(int i) {
        this.c = i;
    }

    public final void a(Proxy proxy) {
        this.e = proxy;
    }

    public abstract Map<String, String> b();

    public final void b(int i) {
        this.d = i;
    }

    public abstract String c();

    public byte[] d() {
        return null;
    }

    String i() {
        byte[] d = d();
        if (d == null || d.length == 0) {
            return c();
        }
        Map a = a();
        if (a == null) {
            return c();
        }
        String a2 = am.a(a);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c()).append("?").append(a2);
        return stringBuffer.toString();
    }

    byte[] j() {
        byte[] d = d();
        if (d != null && d.length != 0) {
            return d;
        }
        String a = am.a(a());
        return !TextUtils.isEmpty(a) ? dd.a(a) : d;
    }
}
