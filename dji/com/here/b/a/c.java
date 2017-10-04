package com.here.b.a;

import android.content.Context;
import android.text.TextUtils;
import com.here.b.d.a.a;

public class c {
    private Integer a;
    private Long b;
    private Context c;
    private String d;
    private Boolean e;
    private Boolean f;
    private Boolean g;
    private String h;
    private a i;

    public c(Context context, String str) {
        this(context, str, null, null);
    }

    public c(Context context, String str, Integer num, Long l) {
        String str2 = "analytics-android client must be initialized with a valid ";
        if (context == null) {
            throw new IllegalArgumentException(str2 + "android context.");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(str2 + "writeKey.");
        } else {
            this.d = str;
            this.c = context;
            if (num == null) {
                com.here.b.d.c.a("Settings the flushSize to default");
            }
            if (l == null) {
                com.here.b.d.c.a("Settings the flushTime to default");
            }
            this.h = "2.0.0.21";
            this.a = num;
            this.b = l;
            this.i = a.INFO;
        }
    }

    public Integer a() {
        return this.a != null ? this.a : null;
    }

    public void a(int i) {
        this.a = Integer.valueOf(i);
    }

    public Long b() {
        return this.b != null ? this.b : null;
    }

    public void a(long j) {
        this.b = Long.valueOf(j);
    }

    public Context c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public Boolean e() {
        return this.e;
    }

    public void a(Boolean bool) {
        this.e = bool;
        if (bool.booleanValue()) {
            com.here.b.d.c.a("Flush while roaming is enabled");
        } else {
            com.here.b.d.c.a("Flush while roaming is disabled");
        }
    }

    public Boolean f() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = Boolean.valueOf(z);
    }

    public Boolean g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public a i() {
        return this.i;
    }

    public void a(a aVar) {
        this.i = aVar;
    }
}
