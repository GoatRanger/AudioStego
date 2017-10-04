package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.List;

public class ev {
    private ek a = a(this.b);
    private Context b;

    public ev(Context context) {
        this.b = context;
    }

    private ek a(Context context) {
        try {
            return new ek(context, ek.a(er.class));
        } catch (Throwable th) {
            eb.a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    public ew a() {
        try {
            if (this.a == null) {
                this.a = a(this.b);
            }
            List b = this.a.b("1=1", ew.class);
            if (b.size() > 0) {
                return (ew) b.get(0);
            }
        } catch (Throwable th) {
            eb.a(th, "UpdateLogDB", "getUpdateLog");
        }
        return null;
    }

    public void a(ew ewVar) {
        if (ewVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b);
                }
                String str = "1=1";
                List b = this.a.b(str, ew.class);
                if (b == null || b.size() == 0) {
                    this.a.a((Object) ewVar);
                } else {
                    this.a.a(str, (Object) ewVar);
                }
            } catch (Throwable th) {
                eb.a(th, "UpdateLogDB", "updateLog");
            }
        }
    }
}
