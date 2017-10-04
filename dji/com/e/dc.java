package com.e;

import android.text.TextUtils;
import com.alipay.sdk.j.i;
import java.util.HashMap;
import java.util.Map;

@g(a = "a")
public class dc {
    @h(a = "a1", b = 6)
    private String a;
    @h(a = "a2", b = 6)
    private String b;
    @h(a = "a6", b = 2)
    private int c;
    @h(a = "a3", b = 6)
    private String d;
    @h(a = "a4", b = 6)
    private String e;
    @h(a = "a5", b = 6)
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

        public dc a() throws ct {
            if (this.f != null) {
                return new dc();
            }
            throw new ct("sdk packages is null");
        }
    }

    private dc() {
        this.c = 1;
        this.k = null;
    }

    private dc(a aVar) {
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
        this.b = dd.b(this.g);
        this.a = dd.b(this.i);
        this.d = dd.b(this.h);
        this.e = dd.b(a(this.k));
        this.f = dd.b(this.j);
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("a1", dd.b(str));
        return f.a(hashMap);
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

    private String[] b(String str) {
        try {
            return str.split(i.b);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String f() {
        return "a6=1";
    }

    public String a() {
        if (TextUtils.isEmpty(this.i) && !TextUtils.isEmpty(this.a)) {
            this.i = dd.c(this.a);
        }
        return this.i;
    }

    public void a(boolean z) {
        this.c = z ? 1 : 0;
    }

    public String b() {
        if (TextUtils.isEmpty(this.g) && !TextUtils.isEmpty(this.b)) {
            this.g = dd.c(this.b);
        }
        return this.g;
    }

    public String c() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.f)) {
            this.j = dd.c(this.f);
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "standard";
        }
        return this.j;
    }

    public boolean d() {
        return this.c == 1;
    }

    public String[] e() {
        if ((this.k == null || this.k.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.k = b(dd.c(this.e));
        }
        return (String[]) this.k.clone();
    }
}
