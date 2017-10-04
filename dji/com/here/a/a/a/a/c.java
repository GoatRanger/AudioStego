package com.here.a.a.a.a;

import com.here.a.a.a.a.t.a;
import com.here.a.a.a.s;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class c {
    public final String a;
    public final ac b;
    public final String c;
    public final String d;
    public final ad<String> e;
    public final ad<t> f;
    public final ad<Date> g;
    public final ad<Date> h;
    public final ad<t> i;
    public final ad<String> j;
    private List<s> k;
    private final Collection<String> l;

    public c(String str, ac acVar, String str2, String str3, List<s> list, String str4, t tVar, Date date, Date date2, t tVar2, String str5, Collection<String> collection) {
        if (str == null || acVar == null || str2 == null || str3 == null) {
            throw new NullPointerException("Alert id, operator, origin and info can't be null.");
        }
        if (list == null) {
            list = Collections.emptyList();
        }
        if (collection == null) {
            collection = Collections.emptyList();
        }
        this.a = str;
        this.b = acVar;
        this.c = str2;
        this.d = str3;
        this.k = list;
        this.e = ad.b(str4);
        this.f = ad.b(tVar);
        this.g = ad.b(date);
        this.h = ad.b(date2);
        this.i = ad.b(tVar2);
        this.j = ad.b(str5);
        this.l = collection;
    }

    public List<s> a() {
        return Collections.unmodifiableList(this.k);
    }

    public Collection<String> b() {
        return Collections.unmodifiableCollection(this.l);
    }

    public static c a(o oVar, Map<String, ac> map) {
        String str;
        t tVar;
        t tVar2 = null;
        String str2 = null;
        if (oVar.b("Branding")) {
            str = null;
            tVar = null;
        } else {
            Iterator it = oVar.c("Branding").d("At").iterator();
            while (it.hasNext()) {
                t tVar3;
                o oVar2 = (o) it.next();
                String i = oVar2.i("@id");
                String a = oVar2.a("$", null);
                if ("name".equalsIgnoreCase(i)) {
                    tVar3 = tVar2;
                } else if (!"image".equalsIgnoreCase(i) || a == null) {
                    a = str2;
                    tVar3 = tVar2;
                } else {
                    tVar3 = new t(a, a.ALERT, true, null, null, null);
                    a = str2;
                }
                str2 = a;
                tVar2 = tVar3;
            }
            str = str2;
            tVar = tVar2;
        }
        String[] split = oVar.a("@sec_ids", "").split("\\s");
        return new c(oVar.i("@id"), (ac) map.get(oVar.i("@operator")), oVar.i("@origin"), oVar.c("Info").i("$"), ak.a(oVar), oVar.a("@severity", null), oVar.b("Link") ? null : t.fromJSON(oVar.c("Link")), oVar.b("@valid_from") ? null : s.a(oVar.i("@valid_from")), oVar.b("@valid_till") ? null : s.a(oVar.i("@valid_till")), tVar, str, split.length > 0 ? Arrays.asList(split) : null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.a.equals(cVar.a) && this.b.equals(cVar.b) && this.c.equals(cVar.c) && this.d.equals(cVar.d) && this.e.equals(cVar.e) && this.f.equals(cVar.f) && this.g.equals(cVar.g) && this.h.equals(cVar.h) && this.i.equals(cVar.i) && this.j.equals(cVar.j) && this.k.equals(cVar.k) && this.l.equals(cVar.l)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + this.j.hashCode()) * 31) + this.k.hashCode()) * 31) + this.l.hashCode();
    }
}
