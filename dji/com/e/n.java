package com.e;

import android.content.Context;
import java.util.List;

public class n {
    private f a;

    public n(Context context) {
        try {
            this.a = new f(context, f.a(m.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(String str, Class<? extends o> cls) {
        this.a.a(o.c(str), (Class) cls);
    }

    public List<? extends o> a(int i, Class<? extends o> cls) {
        List<? extends o> list = null;
        try {
            list = this.a.b(o.c(i), cls);
        } catch (Throwable th) {
            dg.a(th, "LogDB", "ByState");
        }
        return list;
    }

    public void a(o oVar) {
        if (oVar != null) {
            String c = o.c(oVar.b());
            List a = this.a.a(c, oVar.getClass(), true);
            if (a == null || a.size() == 0) {
                this.a.a((Object) oVar, true);
                return;
            }
            Object obj = (o) a.get(0);
            if (oVar.a() == 0) {
                obj.b(obj.c() + 1);
            } else {
                obj.b(0);
            }
            this.a.a(c, obj, true);
        }
    }

    public void a(String str, Class<? extends o> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            dg.a(th, "LogDB", "delLog");
        }
    }

    public void b(o oVar) {
        try {
            this.a.a(o.c(oVar.b()), (Object) oVar);
        } catch (Throwable th) {
            dg.a(th, "LogDB", "updateLogInfo");
        }
    }

    public void b(String str, Class<? extends o> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
