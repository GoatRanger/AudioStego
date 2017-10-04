package com.here.a.a.a.a;

import com.here.a.a.a.s;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ah {
    public final String a;
    public final int b;
    public final long c;
    public final k d;
    public final e e;
    public final ad<Boolean> f;
    public final ad<Boolean> g;
    public final ad<Boolean> h;
    public final ad<String> i;
    private List<aj> j;
    private List<ao> k;

    public ah(String str, int i, long j, k kVar, e eVar, List<aj> list, List<ao> list2, Boolean bool, Boolean bool2, Boolean bool3, String str2) {
        if (str == null || kVar == null || eVar == null) {
            throw new NullPointerException("Route id, duration, departure and arrival can't be null.");
        } else if (j < 0) {
            throw new IllegalArgumentException("Route duration can't be below zero.");
        } else if (i < 0) {
            throw new IllegalArgumentException("Route number of transfers can't be below zero.");
        } else if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Route should contain at least one RouteSection.");
        } else {
            if (list2 == null) {
                list2 = Collections.emptyList();
            }
            this.a = str;
            this.b = i;
            this.c = j;
            this.d = kVar;
            this.e = eVar;
            this.j = list;
            this.k = list2;
            this.f = ad.b(bool);
            this.g = ad.b(bool2);
            this.h = ad.b(bool3);
            this.i = ad.b(str2);
        }
    }

    public List<ao> a() {
        return Collections.unmodifiableList(this.k);
    }

    public List<aj> b() {
        return Collections.unmodifiableList(this.j);
    }

    public static ah a(o oVar, List<w> list, Collection<t> collection, Collection<c> collection2) {
        p d;
        Iterator it;
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        List list2 = null;
        if (!oVar.b("Tariff")) {
            o c = oVar.c("Tariff");
            if (!c.b("Tickets")) {
                d = c.d("Tickets");
                if (d.a() > 0) {
                    list2 = new ArrayList(d.a());
                    it = d.iterator();
                    while (it.hasNext()) {
                        list2.add(ao.fromJSON((o) it.next()));
                    }
                }
            }
        }
        d = oVar.c("Sections").d("Sec");
        List arrayList = new ArrayList(d.a());
        it = d.iterator();
        while (it.hasNext()) {
            arrayList.add(aj.a((o) it.next(), list2, list, collection, collection2));
        }
        String i = oVar.i("@id");
        int intValue = oVar.j("@transfers").intValue();
        long b = s.b(oVar.i("@duration"));
        k fromJSON = k.fromJSON(oVar.c("Dep"));
        e fromJSON2 = e.fromJSON(oVar.c("Arr"));
        if (oVar.b("@alt")) {
            bool = null;
        } else {
            bool = Boolean.valueOf(oVar.j("@alt").intValue() == 1);
        }
        if (oVar.b("@has_alt")) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(oVar.j("@has_alt").intValue() == 1);
        }
        if (oVar.b("@ridable")) {
            bool3 = null;
        } else {
            bool3 = Boolean.valueOf(oVar.j("@ridable").intValue() == 1);
        }
        return new ah(i, intValue, b, fromJSON, fromJSON2, arrayList, list2, bool, bool2, bool3, oVar.a("@walk_ctx", null));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ah ahVar = (ah) obj;
        if (this.b == ahVar.b && this.c == ahVar.c && this.a.equals(ahVar.a) && this.d.equals(ahVar.d) && this.e.equals(ahVar.e) && this.i.equals(ahVar.i) && this.j.equals(ahVar.j) && this.k.equals(ahVar.k)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((this.a.hashCode() * 31) + this.b) * 31) + ((int) (this.c ^ (this.c >>> 32)))) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.i.hashCode()) * 31) + this.j.hashCode()) * 31) + this.k.hashCode();
    }
}
