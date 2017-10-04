package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.List;

public class eu {
    private ek a;
    private Context b;

    public eu(Context context, boolean z) {
        this.b = context;
        this.a = a(this.b, z);
    }

    private ek a(Context context, boolean z) {
        try {
            return new ek(context, ek.a(er.class));
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
                return null;
            }
            eb.a(th, "SDKDB", "getDB");
            return null;
        }
    }

    public void a(dv dvVar) {
        if (dvVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b, false);
                }
                String a = dv.a(dvVar.a());
                List b = this.a.b(a, dv.class);
                if (b == null || b.size() == 0) {
                    this.a.a((Object) dvVar);
                } else {
                    this.a.a(a, (Object) dvVar);
                }
            } catch (Throwable th) {
                eb.a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }

    public List<dv> a() {
        List<dv> list = null;
        try {
            list = this.a.a(dv.f(), dv.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return list;
    }
}
