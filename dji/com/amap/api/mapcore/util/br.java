package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class br {
    private static br a;
    private ga b;
    private LinkedHashMap<String, gc> c = new LinkedHashMap();
    private boolean d = true;

    public static br a(int i) {
        return a(true, i);
    }

    private static synchronized br a(boolean z, int i) {
        br brVar;
        synchronized (br.class) {
            try {
                if (a == null) {
                    a = new br(z, i);
                } else if (z) {
                    if (a.b == null) {
                        a.b = ga.a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            brVar = a;
        }
        return brVar;
    }

    private br(boolean z, int i) {
        if (z) {
            try {
                this.b = ga.a(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a() {
        synchronized (this.c) {
            if (this.c.size() < 1) {
                return;
            }
            for (Entry entry : this.c.entrySet()) {
                entry.getKey();
                ((bn) entry.getValue()).b();
            }
            this.c.clear();
        }
    }

    public void a(bq bqVar) {
        synchronized (this.c) {
            bn bnVar = (bn) this.c.get(bqVar.b());
            if (bnVar == null) {
                return;
            }
            bnVar.b();
        }
    }

    public void a(bq bqVar, Context context, AMap aMap) throws dk {
        if (this.b == null) {
        }
        if (!this.c.containsKey(bqVar.b())) {
            bn bnVar = new bn((cg) bqVar, context.getApplicationContext(), aMap);
            synchronized (this.c) {
                this.c.put(bqVar.b(), bnVar);
            }
        }
        this.b.a((gc) this.c.get(bqVar.b()));
    }

    public void b() {
        a();
        ga gaVar = this.b;
        ga.a();
        this.b = null;
        a = null;
    }

    public void b(bq bqVar) {
        bn bnVar = (bn) this.c.get(bqVar.b());
        if (bnVar != null) {
            synchronized (this.c) {
                bnVar.c();
                this.c.remove(bqVar.b());
            }
        }
    }
}
