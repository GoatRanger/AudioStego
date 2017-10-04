package com.e;

import android.content.Context;
import java.util.List;

public class q {
    private f a = a(this.b);
    private Context b;

    public q(Context context) {
        this.b = context;
    }

    private f a(Context context) {
        try {
            return new f(context, f.a(m.class));
        } catch (Throwable th) {
            dg.a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    public r a() {
        try {
            if (this.a == null) {
                this.a = a(this.b);
            }
            List b = this.a.b("1=1", r.class);
            if (b.size() > 0) {
                return (r) b.get(0);
            }
        } catch (Throwable th) {
            dg.a(th, "UpdateLogDB", "getUpdateLog");
        }
        return null;
    }

    public void a(r rVar) {
        if (rVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b);
                }
                String str = "1=1";
                List b = this.a.b(str, r.class);
                if (b == null || b.size() == 0) {
                    this.a.a((Object) rVar);
                } else {
                    this.a.a(str, (Object) rVar);
                }
            } catch (Throwable th) {
                dg.a(th, "UpdateLogDB", "updateLog");
            }
        }
    }
}
