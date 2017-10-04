package com.flurry.sdk;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ij {
    private static ij a = null;
    private static final String b = ij.class.getSimpleName();
    private final ie<String, iw<ii<?>>> c = new ie();
    private final ie<iw<ii<?>>, String> d = new ie();

    public static synchronized ij a() {
        ij ijVar;
        synchronized (ij.class) {
            if (a == null) {
                a = new ij();
            }
            ijVar = a;
        }
        return ijVar;
    }

    public static synchronized void b() {
        synchronized (ij.class) {
            if (a != null) {
                a.c();
                a = null;
            }
        }
    }

    private ij() {
    }

    public synchronized void a(String str, ii<?> iiVar) {
        if (!(TextUtils.isEmpty(str) || iiVar == null)) {
            Object iwVar = new iw(iiVar);
            if (!this.c.c(str, iwVar)) {
                this.c.a((Object) str, iwVar);
                this.d.a(iwVar, (Object) str);
            }
        }
    }

    public synchronized void b(String str, ii<?> iiVar) {
        if (!TextUtils.isEmpty(str)) {
            iw iwVar = new iw(iiVar);
            this.c.b(str, iwVar);
            this.d.b(iwVar, str);
        }
    }

    public synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (iw b : this.c.a((Object) str)) {
                this.d.b(b, str);
            }
            this.c.b(str);
        }
    }

    public synchronized void a(ii<?> iiVar) {
        if (iiVar != null) {
            Object iwVar = new iw(iiVar);
            for (String b : this.d.a(iwVar)) {
                this.c.b(b, iwVar);
            }
            this.d.b(iwVar);
        }
    }

    public synchronized void c() {
        this.c.a();
        this.d.a();
    }

    public synchronized int b(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            i = 0;
        } else {
            i = this.c.a((Object) str).size();
        }
        return i;
    }

    public synchronized List<ii<?>> c(String str) {
        List<ii<?>> emptyList;
        if (TextUtils.isEmpty(str)) {
            emptyList = Collections.emptyList();
        } else {
            List<ii<?>> arrayList = new ArrayList();
            Iterator it = this.c.a((Object) str).iterator();
            while (it.hasNext()) {
                ii iiVar = (ii) ((iw) it.next()).get();
                if (iiVar == null) {
                    it.remove();
                } else {
                    arrayList.add(iiVar);
                }
            }
            emptyList = arrayList;
        }
        return emptyList;
    }

    public void a(final ih ihVar) {
        if (ihVar != null) {
            for (final ii iiVar : c(ihVar.a())) {
                hz.a().b(new kb(this) {
                    final /* synthetic */ ij c;

                    public void a() {
                        iiVar.a(ihVar);
                    }
                });
            }
        }
    }
}
