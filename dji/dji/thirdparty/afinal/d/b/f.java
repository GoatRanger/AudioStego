package dji.thirdparty.afinal.d.b;

import dji.thirdparty.afinal.e.b;
import dji.thirdparty.afinal.g.a;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class f {
    private static final HashMap<String, f> h = new HashMap();
    public final HashMap<String, e> a = new HashMap();
    public final HashMap<String, d> b = new HashMap();
    public final HashMap<String, c> c = new HashMap();
    private String d;
    private String e;
    private a f;
    private boolean g;

    private f() {
    }

    public static f a(Class<?> cls) {
        if (cls == null) {
            throw new b("table info get error,because the clazz is null");
        }
        f fVar = (f) h.get(cls.getName());
        if (fVar == null) {
            f fVar2 = new f();
            fVar2.c(a.a(cls));
            fVar2.b(cls.getName());
            Field c = a.c(cls);
            if (c != null) {
                a aVar = new a();
                aVar.b(dji.thirdparty.afinal.g.b.a(c));
                aVar.a(c.getName());
                aVar.b(dji.thirdparty.afinal.g.b.c(cls, c));
                aVar.a(dji.thirdparty.afinal.g.b.a(cls, c));
                aVar.b(c.getType());
                aVar.a(c);
                fVar2.a(aVar);
                List<e> e = a.e(cls);
                if (e != null) {
                    for (e eVar : e) {
                        if (eVar != null) {
                            fVar2.a.put(eVar.c(), eVar);
                        }
                    }
                }
                List<c> f = a.f(cls);
                if (f != null) {
                    for (c cVar : f) {
                        if (cVar != null) {
                            fVar2.c.put(cVar.c(), cVar);
                        }
                    }
                }
                List<d> g = a.g(cls);
                if (g != null) {
                    for (d dVar : g) {
                        if (dVar != null) {
                            fVar2.b.put(dVar.c(), dVar);
                        }
                    }
                }
                h.put(cls.getName(), fVar2);
                fVar = fVar2;
            } else {
                throw new b("the class[" + cls + "]'s idField is null , \n you can define _id,id property or use annotation @id to solution this exception");
            }
        }
        if (fVar != null) {
            return fVar;
        }
        throw new b("the class[" + cls + "]'s table is null");
    }

    public static f a(String str) {
        try {
            return a(Class.forName(str));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String a() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String b() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public a c() {
        return this.f;
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public boolean d() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }
}
