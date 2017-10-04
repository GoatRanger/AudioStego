package com.e;

import android.content.Context;
import java.util.List;

public class p {
    private f a;
    private Context b;

    public p(Context context, boolean z) {
        this.b = context;
        this.a = a(this.b, z);
    }

    private f a(Context context, boolean z) {
        try {
            return new f(context, f.a(m.class));
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
                return null;
            }
            dg.a(th, "SDKDB", "getDB");
            return null;
        }
    }

    public List<dc> a() {
        List<dc> list = null;
        try {
            list = this.a.a(dc.f(), dc.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return list;
    }

    public void a(dc dcVar) {
        if (dcVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b, false);
                }
                String a = dc.a(dcVar.a());
                List b = this.a.b(a, dc.class);
                if (b == null || b.size() == 0) {
                    this.a.a((Object) dcVar);
                } else {
                    this.a.a(a, (Object) dcVar);
                }
            } catch (Throwable th) {
                dg.a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }
}
