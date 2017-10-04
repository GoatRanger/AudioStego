package com.flurry.sdk;

import android.content.Context;
import java.io.File;
import java.util.List;
import java.util.Map;

public class gh {
    private static final String b = gh.class.getSimpleName();
    boolean a;
    private final gi c;
    private final File d;
    private String e;

    public gh() {
        this(hz.a().c());
    }

    public gh(Context context) {
        this.c = new gi();
        this.d = context.getFileStreamPath(".flurryinstallreceiver.");
        in.a(3, b, "Referrer file name if it exists:  " + this.d);
    }

    public synchronized void a() {
        this.d.delete();
        this.e = null;
        this.a = true;
    }

    public synchronized Map<String, List<String>> a(boolean z) {
        Map<String, List<String>> a;
        b();
        a = this.c.a(this.e);
        if (z) {
            a();
        }
        return a;
    }

    public synchronized void a(String str) {
        this.a = true;
        b(str);
        c();
    }

    private void b(String str) {
        if (str != null) {
            this.e = str;
        }
    }

    private void b() {
        if (!this.a) {
            this.a = true;
            in.a(4, b, "Loading referrer info from file: " + this.d.getAbsolutePath());
            String c = jy.c(this.d);
            in.a(b, "Referrer file contents: " + c);
            b(c);
        }
    }

    private void c() {
        jy.a(this.d, this.e);
    }
}
