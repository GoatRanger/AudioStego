package com.here.b.c;

import android.text.TextUtils;
import com.a.a.i;
import com.alipay.sdk.b.c;
import java.util.LinkedHashMap;
import java.util.Map;

public class b extends i {
    static final /* synthetic */ boolean a = (!b.class.desiredAssertionStatus());
    private Map<String, Object> b = new LinkedHashMap();

    public b() {
        a(false);
        a("https://collector.data.here.com/fwd");
        b("https://collector.data.here.com/fwd2");
        a(20);
        a((long) a.a);
        c(true);
        b(false);
    }

    public void a(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Analytics Options #flushAt must be greater than 0.");
        }
        a("flushAt", Integer.valueOf(i));
    }

    public void a(long j) {
        if (j <= 50) {
            throw new IllegalArgumentException("Analytics Options #flushAfter must be greater than 50.");
        }
        a("flushAfter", Long.valueOf(j));
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Analytics Options #host must be non-null or empty.");
        }
        a(c.f, str);
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Analytics Options #settingsHost must be non-null or empty.");
        }
        a("settingsHost", str);
    }

    public void a(boolean z) {
        a("debug", Boolean.valueOf(z));
    }

    public void b(boolean z) {
        a("flushOnRoaming", Boolean.valueOf(z));
    }

    public void c(boolean z) {
        a("offlineMode", Boolean.valueOf(z));
    }

    public Object c(String str) {
        if (!TextUtils.isEmpty(str)) {
            return this.b.get(str);
        }
        throw new IllegalArgumentException("Key cannot be null");
    }

    private void a(String str, Object obj) {
        if (a || str != null) {
            this.b.put(str, obj);
            return;
        }
        throw new AssertionError();
    }
}
