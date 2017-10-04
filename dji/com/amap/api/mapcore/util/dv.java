package com.amap.api.mapcore.util;

import android.text.TextUtils;
import com.alipay.sdk.j.i;
import java.util.HashMap;
import java.util.Map;

@el(a = "a")
public class dv {
    @em(a = "a1", b = 6)
    private String a;
    @em(a = "a2", b = 6)
    private String b;
    @em(a = "a6", b = 2)
    private int c;
    @em(a = "a3", b = 6)
    private String d;
    @em(a = "a4", b = 6)
    private String e;
    @em(a = "a5", b = 6)
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String[] k;

    public static class a {
        private String a;
        private String b;
        private String c;
        private boolean d = true;
        private String e = "standard";
        private String[] f = null;

        public a(String str, String str2, String str3) {
            this.a = str2;
            this.c = str3;
            this.b = str;
        }

        public a a(String[] strArr) {
            this.f = (String[]) strArr.clone();
            return this;
        }

        public dv a() throws dk {
            if (this.f != null) {
                return new dv();
            }
            throw new dk("sdk packages is null");
        }
    }

    private dv() {
        this.c = 1;
        this.k = null;
    }

    private dv(a aVar) {
        int i = 1;
        this.c = 1;
        this.k = null;
        this.g = aVar.a;
        this.i = aVar.b;
        this.h = aVar.c;
        if (!aVar.d) {
            i = 0;
        }
        this.c = i;
        this.j = aVar.e;
        this.k = aVar.f;
        this.b = dx.b(this.g);
        this.a = dx.b(this.i);
        this.d = dx.b(this.h);
        this.e = dx.b(a(this.k));
        this.f = dx.b(this.j);
    }

    public void a(boolean z) {
        this.c = z ? 1 : 0;
    }

    public String a() {
        if (TextUtils.isEmpty(this.i) && !TextUtils.isEmpty(this.a)) {
            this.i = dx.c(this.a);
        }
        return this.i;
    }

    public String b() {
        if (TextUtils.isEmpty(this.g) && !TextUtils.isEmpty(this.b)) {
            this.g = dx.c(this.b);
        }
        return this.g;
    }

    public String c() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.d)) {
            this.h = dx.c(this.d);
        }
        return this.h;
    }

    public String d() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.f)) {
            this.j = dx.c(this.f);
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "standard";
        }
        return this.j;
    }

    public String[] e() {
        if ((this.k == null || this.k.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.k = b(dx.c(this.e));
        }
        return (String[]) this.k.clone();
    }

    private String[] b(String str) {
        try {
            return str.split(i.b);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private String a(String[] strArr) {
        String str = null;
        if (strArr != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : strArr) {
                    stringBuilder.append(append).append(i.b);
                }
                str = stringBuilder.toString();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return str;
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("a1", dx.b(str));
        return ek.a(hashMap);
    }

    public static String f() {
        return "a6=1";
    }
}
