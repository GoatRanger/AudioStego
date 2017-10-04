package com.here.a.a.a.a;

import com.here.a.a.a.j.a;
import com.here.a.a.a.s;
import dji.sdksharedlib.b.d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ai {
    public final String a;
    public final String b;
    public final a c;
    public final ad<Boolean> d;
    public final ad<Boolean> e;
    public final ad<Boolean> f;
    public final ad<Boolean> g;
    public final ad<Date> h;
    private List<ah> i;
    private List<w> j;
    private Collection<ac> k;
    private Collection<c> l;
    private Collection<t> m;

    public ai(String str, String str2, a aVar, List<ah> list, List<w> list2, Collection<ac> collection, Collection<c> collection2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Date date, Collection<t> collection3) {
        if (str == null || str2 == null) {
            throw new NullPointerException("RouteList context and serviceUrl can't be null.");
        }
        if (list == null) {
            list = Collections.emptyList();
        }
        if (list2 == null) {
            list2 = Collections.emptyList();
        }
        if (collection == null) {
            collection = Collections.emptySet();
        }
        if (collection2 == null) {
            collection2 = Collections.emptySet();
        }
        if (aVar == null) {
            aVar = a.BOTH;
        }
        if (collection3 == null) {
            collection3 = Collections.emptyList();
        }
        this.b = str2;
        this.a = str;
        this.c = aVar;
        this.i = list;
        this.j = list2;
        this.k = collection;
        this.l = collection2;
        this.g = ad.b(bool4);
        this.d = ad.b(bool);
        this.e = ad.b(bool2);
        this.f = ad.b(bool3);
        this.h = ad.b(date);
        this.m = collection3;
    }

    public List<ah> a() {
        return Collections.unmodifiableList(this.i);
    }

    public Collection<ac> b() {
        return Collections.unmodifiableCollection(this.k);
    }

    public Collection<c> c() {
        return Collections.unmodifiableCollection(this.l);
    }

    public Collection<t> d() {
        return Collections.unmodifiableCollection(this.m);
    }

    public static ai fromJSON(o oVar) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        Boolean bool4;
        Date date;
        o c = oVar.c("Connections");
        Collection b = b(c);
        o f = oVar.f("Guidance");
        List a = f != null ? a(f) : null;
        Collection c2 = c(c);
        Collection a2 = a(c, b);
        String i = c.i("@context");
        String i2 = oVar.i("@serviceUrl");
        a a3 = a.a(c.a("@allow_direction", null));
        List a4 = a(c, a, c2, a2);
        if (c.b("@sup_max_dist")) {
            bool = null;
        } else {
            bool = Boolean.valueOf(c.j("@sup_max_dist").intValue() == 1);
        }
        if (c.b("@sup_changes")) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(c.j("@sup_changes").intValue() == 1);
        }
        if (c.b("@sup_prod")) {
            bool3 = null;
        } else {
            bool3 = Boolean.valueOf(c.j("@sup_prod").intValue() == 1);
        }
        if (c.b("@sup_speed")) {
            bool4 = null;
        } else {
            bool4 = Boolean.valueOf(c.j("@sup_speed").intValue() == 1);
        }
        if (c.b("@valid_until")) {
            date = null;
        } else {
            date = s.a(c.i("@valid_until"));
        }
        return new ai(i, i2, a3, a4, a, b, a2, bool, bool2, bool3, bool4, date, c2);
    }

    private static List<ah> a(o oVar, List<w> list, Collection<t> collection, Collection<c> collection2) {
        if (!oVar.b(d.ck)) {
            p d = oVar.d(d.ck);
            if (d.a() > 0) {
                List<ah> arrayList = new ArrayList(d.a());
                Iterator it = d.iterator();
                while (it.hasNext()) {
                    arrayList.add(ah.a((o) it.next(), list, collection, collection2));
                }
                return arrayList;
            }
        }
        return null;
    }

    protected static List<w> a(o oVar) {
        p e = oVar.e("Maneuvers");
        if (e == null || e.a() <= 0) {
            return null;
        }
        List<w> arrayList = new ArrayList(e.a());
        Iterator it = e.iterator();
        while (it.hasNext()) {
            arrayList.add(w.fromJSON((o) it.next()));
        }
        return arrayList;
    }

    protected static Collection<ac> b(o oVar) {
        if (!oVar.b("Operators")) {
            o c = oVar.c("Operators");
            if (!c.b("Op")) {
                p d = c.d("Op");
                if (d.a() > 0) {
                    Collection<ac> arrayList = new ArrayList(d.a());
                    Iterator it = d.iterator();
                    while (it.hasNext()) {
                        arrayList.add(ac.fromJSON((o) it.next()));
                    }
                    return arrayList;
                }
            }
        }
        return null;
    }

    protected static Collection<t> c(o oVar) {
        o f = oVar.f("Attributions");
        if (f != null) {
            p e = f.e("Link");
            if (e != null && e.a() > 0) {
                Collection<t> arrayList = new ArrayList(e.a());
                Iterator it = e.iterator();
                while (it.hasNext()) {
                    arrayList.add(t.fromJSON((o) it.next()));
                }
                return arrayList;
            }
        }
        return null;
    }

    private static Collection<c> a(o oVar, Collection<ac> collection) {
        if (!oVar.b("Alerts")) {
            o c = oVar.c("Alerts");
            if (!c.b("Alert")) {
                p d = c.d("Alert");
                if (d.a() > 0) {
                    Map hashMap = new HashMap();
                    for (ac acVar : collection) {
                        if (acVar.b.c()) {
                            hashMap.put(acVar.b.b(), acVar);
                        }
                    }
                    Collection<c> arrayList = new ArrayList(d.a());
                    Iterator it = d.iterator();
                    while (it.hasNext()) {
                        arrayList.add(c.a((o) it.next(), hashMap));
                    }
                    return arrayList;
                }
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ai aiVar = (ai) obj;
        if (this.a.equals(aiVar.a) && this.c == aiVar.c && this.d.equals(aiVar.d) && this.e.equals(aiVar.e) && this.f.equals(aiVar.f) && this.g.equals(aiVar.g) && this.h.equals(aiVar.h) && this.i.equals(aiVar.i) && this.k.equals(aiVar.k) && this.m.equals(aiVar.m)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((this.a.hashCode() * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + this.k.hashCode()) * 31) + this.m.hashCode();
    }
}
