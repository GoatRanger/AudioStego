package com.alipay.e.a.a.a;

import android.content.Context;
import com.alipay.android.a.a.a.ad;
import com.alipay.android.a.a.a.e;
import com.alipay.android.a.a.a.o;
import com.alipay.f.a.a.a.a;
import com.alipay.f.a.a.b.b.d;
import org.json.JSONObject;

public final class b implements a {
    private static b f = null;
    private static d g;
    private Context a = null;
    private ad b = null;
    private a c = null;
    private com.alipay.f.a.a.b.b d = null;
    private com.alipay.f.a.a.b.a e = null;

    private b(Context context) {
        this.a = context;
        try {
            e eVar = new e();
            eVar.a(com.alipay.e.a.a.a.a.a.a());
            this.b = new o(context);
            this.c = (a) this.b.a(a.class, eVar);
            this.d = (com.alipay.f.a.a.b.b) this.b.a(com.alipay.f.a.a.b.b.class, eVar);
            this.e = (com.alipay.f.a.a.b.a) this.b.a(com.alipay.f.a.a.b.a.class, eVar);
        } catch (Throwable e) {
            com.alipay.e.a.a.b.d.a(e);
        }
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (f == null) {
                f = new b(context);
            }
            bVar = f;
        }
        return bVar;
    }

    public final com.alipay.f.a.a.b.b.b a(String str, String str2, String str3, String str4) {
        com.alipay.f.a.a.b.b.b bVar = null;
        try {
            com.alipay.f.a.a.b.a.a aVar = new com.alipay.f.a.a.b.a.a();
            aVar.a = str;
            aVar.c = str4;
            aVar.b = str2;
            aVar.d = str3;
            bVar = this.e.a(aVar);
        } catch (Exception e) {
        }
        return bVar;
    }

    public final d a(com.alipay.f.a.a.b.a.b bVar) {
        if (this.d != null) {
            try {
                g = null;
                new Thread(new c(this, bVar)).start();
                int i = 300000;
                while (g == null && i >= 0) {
                    Thread.sleep(50);
                    i -= 50;
                }
            } catch (Throwable e) {
                com.alipay.e.a.a.b.d.a(e);
            }
        }
        return g;
    }

    public final boolean a(String str) {
        if (com.alipay.e.a.a.b.a.a(str)) {
            return false;
        }
        boolean booleanValue;
        if (this.c != null) {
            String str2 = null;
            try {
                str2 = this.c.a(com.alipay.e.a.a.b.a.f(str));
            } catch (Exception e) {
            }
            if (!com.alipay.e.a.a.b.a.a(str2)) {
                try {
                    booleanValue = ((Boolean) new JSONObject(str2).get("success")).booleanValue();
                } catch (Throwable e2) {
                    com.alipay.e.a.a.b.d.a(e2);
                }
                return booleanValue;
            }
        }
        booleanValue = false;
        return booleanValue;
    }
}
