package dji.sdksharedlib.b;

import dji.sdksharedlib.b.b.a;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.hardware.abstractions.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class d$a {
    private a a;
    private d b;
    private Set<Class> c;
    private Map<Class, d> d;
    private d e;

    public d$a(a aVar, d dVar) {
        int length;
        int i = 0;
        this.a = aVar;
        this.b = dVar;
        if (aVar != null) {
            d[] a = aVar.a();
            if (a != null && a.length > 0) {
                for (d dVar2 : a) {
                    if (dVar2.f() != null && dVar2.f().length > 0) {
                        this.c = new HashSet();
                        for (Object add : dVar2.f()) {
                            this.c.add(add);
                        }
                    }
                    if (dVar2.e() == null || dVar2.e().length == 0) {
                        this.e = dVar2;
                    }
                    if (dVar2.e() != null && dVar2.e().length > 0) {
                        this.d = new HashMap();
                        for (Object add2 : dVar2.e()) {
                            this.d.put(add2, dVar2);
                        }
                    }
                }
            }
        }
        if (dVar != null) {
            if (dVar.e() == null || dVar.e().length == 0) {
                this.e = dVar;
            }
            if (dVar.e() != null && dVar.e().length > 0) {
                this.d = new HashMap();
                Class[] e = dVar.e();
                length = e.length;
                while (i < length) {
                    this.d.put(e[i], dVar);
                    i++;
                }
            }
        }
    }

    public boolean a(Class<? extends b> cls) {
        if (cls == null) {
            return false;
        }
        if (this.c != null && this.c.contains(cls)) {
            return false;
        }
        if ((this.d == null || !this.d.containsKey(cls)) && this.e == null) {
            return false;
        }
        return true;
    }

    public d b(Class<? extends d> cls) {
        if (cls == null) {
            return null;
        }
        if (this.d == null || !this.d.containsKey(cls)) {
            return this.e;
        }
        return (d) this.d.get(cls);
    }
}
