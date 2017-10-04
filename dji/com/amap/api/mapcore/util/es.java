package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.List;

public class es {
    private ek a;

    public es(Context context) {
        try {
            this.a = new ek(context, ek.a(er.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, Class<? extends et> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            eb.a(th, "LogDB", "delLog");
        }
    }

    public void b(String str, Class<? extends et> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(String str, Class<? extends et> cls) {
        this.a.a(et.c(str), (Class) cls);
    }

    public List<? extends et> a(int i, Class<? extends et> cls) {
        List<? extends et> list = null;
        try {
            list = this.a.b(et.c(i), cls);
        } catch (Throwable th) {
            eb.a(th, "LogDB", "ByState");
        }
        return list;
    }

    public void a(et etVar) {
        if (etVar != null) {
            String c = et.c(etVar.b());
            List a = this.a.a(c, etVar.getClass(), true);
            if (a == null || a.size() == 0) {
                this.a.a((Object) etVar, true);
                return;
            }
            Object obj = (et) a.get(0);
            if (etVar.a() == 0) {
                obj.b(obj.c() + 1);
            } else {
                obj.b(0);
            }
            this.a.a(c, obj, true);
        }
    }

    public void b(et etVar) {
        try {
            this.a.a(et.c(etVar.b()), (Object) etVar);
        } catch (Throwable th) {
            eb.a(th, "LogDB", "updateLogInfo");
        }
    }
}
