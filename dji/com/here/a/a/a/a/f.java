package com.here.a.a.a.a;

import com.here.a.a.a.s;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class f {
    public final String a;
    public final String b;
    public final Date c;
    public final Date d;
    public final m e;
    public final ad<String> f;
    public final ad<Integer> g;
    public final ad<Double> h;
    public final ad<String> i;
    public final ad<Integer> j;
    public final ad<Integer> k;
    public final ad<Float> l;
    public final ad<Integer> m;
    public final ad<y> n;
    private final List<ac> o;
    private final List<ae> p;

    public f(String str, String str2, Date date, Date date2, m mVar, String str3, Integer num, Double d, String str4, Integer num2, Integer num3, Float f, Integer num4, y yVar, List<ac> list, List<ae> list2) {
        if (str == null || str2 == null || date == null || date2 == null || mVar == null) {
            throw new IllegalArgumentException("Name, Country, Created, Updated and Coordinates can't be null.");
        }
        this.a = str;
        this.b = str2;
        this.c = date;
        this.d = date2;
        this.e = mVar;
        this.f = ad.b(str3);
        this.g = ad.b(num);
        this.h = ad.b(d);
        this.i = ad.b(str4);
        this.j = ad.b(num2);
        this.k = ad.b(num3);
        this.l = ad.b(f);
        this.m = ad.b(num4);
        this.n = ad.b(yVar);
        if (list == null) {
            list = Collections.emptyList();
        }
        this.o = list;
        if (list2 == null) {
            list2 = Collections.emptyList();
        }
        this.p = list2;
    }

    public Collection<ac> a() {
        return Collections.unmodifiableCollection(this.o);
    }

    public Collection<ae> b() {
        return Collections.unmodifiableCollection(this.p);
    }

    public static f fromJSON(o oVar) {
        p e;
        List arrayList;
        Iterator it;
        y yVar;
        List list = null;
        o f = oVar.f("Operators");
        if (f != null) {
            e = f.e("Op");
            if (e != null && e.a() > 0) {
                arrayList = new ArrayList(e.a());
                it = e.iterator();
                while (it.hasNext()) {
                    arrayList.add(ac.fromJSON((o) it.next()));
                }
            }
        }
        List list2 = null;
        f = oVar.f("Providers");
        if (f != null) {
            e = f.e("Pr");
            if (e != null && e.a() > 0) {
                arrayList = new ArrayList(e.a());
                it = e.iterator();
                while (it.hasNext()) {
                    arrayList.add(ae.fromJSON((o) it.next()));
                }
            }
        }
        Integer num = null;
        Float f2 = null;
        Integer num2 = null;
        o f3 = oVar.f("Cvg");
        if (f3 != null) {
            num2 = f3.b("@lines") ? null : f3.j("@lines");
            f2 = f3.b("@quality") ? null : f3.g("@quality");
            num = f3.b("@stops") ? null : f3.j("@stops");
        }
        String i = oVar.i("@name");
        String i2 = oVar.i("@country");
        Date a = s.a(oVar.i("@created"));
        Date a2 = s.a(oVar.i("@updated"));
        m mVar = new m(oVar.h("@y").doubleValue(), oVar.h("@x").doubleValue());
        String a3 = oVar.a("@display_name", null);
        Integer j = oVar.b("@distance") ? null : oVar.j("@distance");
        Double h = oVar.b("@relevancy") ? null : oVar.h("@relevancy");
        String a4 = oVar.a("@state", null);
        Integer j2 = oVar.b("Pop") ? null : oVar.j("Pop");
        if (oVar.b("MissingCoverage")) {
            yVar = null;
        } else {
            yVar = y.fromJSON(oVar.c("MissingCoverage"));
        }
        return new f(i, i2, a, a2, mVar, a3, j, h, a4, j2, num, f2, num2, yVar, list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        if (this.a.equals(fVar.a) && this.b.equals(fVar.b) && this.c.equals(fVar.c) && this.d.equals(fVar.d) && this.e.equals(fVar.e) && this.f.equals(fVar.f) && this.g.equals(fVar.g) && this.h.equals(fVar.h) && this.i.equals(fVar.i) && this.j.equals(fVar.j) && this.k.equals(fVar.k) && this.l.equals(fVar.l) && this.m.equals(fVar.m) && this.n.equals(fVar.n) && this.o.equals(fVar.o) && this.p.equals(fVar.p)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + this.j.hashCode()) * 31) + this.k.hashCode()) * 31) + this.l.hashCode()) * 31) + this.m.hashCode()) * 31) + this.n.hashCode()) * 31) + this.o.hashCode()) * 31) + this.p.hashCode();
    }
}
