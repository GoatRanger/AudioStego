package com.here.a.a.a.a;

import com.here.a.a.a.i.d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class aj {
    private List<an> a;
    public final k b;
    public final e c;
    public final int d;
    public final long e;
    public final ad<String> f;
    public final ad<String> g;
    public final ad<Integer> h;
    public final ad<n> i;
    private List<v> j;
    private Collection<t> k;
    private Collection<c> l;

    public aj(k kVar, e eVar, int i, long j, String str, String str2, Integer num, n nVar, List<v> list) {
        if (eVar == null || kVar == null) {
            throw new NullPointerException("RouteSection arrival and departure can't be null.");
        } else if (i < 0 || j < 0) {
            throw new IllegalArgumentException("Distance and duration can't be negative.");
        } else {
            this.d = i;
            this.e = j;
            this.b = kVar;
            this.c = eVar;
            this.f = ad.b(str);
            this.g = ad.b(str2);
            this.h = ad.b(num);
            this.i = ad.b(nVar);
            this.a = Collections.emptyList();
            if (list == null) {
                list = Collections.emptyList();
            }
            this.j = list;
            this.k = Collections.emptyList();
            this.l = Collections.emptyList();
        }
    }

    public List<v> b() {
        return Collections.unmodifiableList(this.j);
    }

    public Collection<t> c() {
        return Collections.unmodifiableCollection(this.k);
    }

    public Collection<c> d() {
        return Collections.unmodifiableCollection(this.l);
    }

    public List<an> e() {
        return Collections.unmodifiableList(this.a);
    }

    public boolean f() {
        return this instanceof ap;
    }

    public d g() {
        if (f()) {
            return d.UNKNOWN;
        }
        return (d) ((s) this.b.c.b()).b.c(d.UNKNOWN);
    }

    public static aj a(o oVar, List<ao> list, List<w> list2, Collection<t> collection, Collection<c> collection2) {
        aj a;
        if (!oVar.b("Walk")) {
            a = ap.a(oVar, list2);
        } else if (oVar.b("Journey")) {
            throw new RuntimeException("Unknown type of RouteSection:\n" + oVar.toString());
        } else {
            a = q.fromJSON(oVar);
        }
        if (a.f.c()) {
            Collection arrayList;
            if (list != null) {
                List arrayList2 = new ArrayList();
                for (ao it : list) {
                    Iterator it2 = it.iterator();
                    while (it2.hasNext()) {
                        an anVar = (an) it2.next();
                        if (anVar.a().contains(a.f.b())) {
                            arrayList2.add(anVar);
                        }
                    }
                }
                if (!arrayList2.isEmpty()) {
                    a.a = arrayList2;
                }
            }
            if (!(collection == null || collection.isEmpty())) {
                arrayList = new ArrayList();
                for (t tVar : collection) {
                    if (tVar.a().contains(a.f.b())) {
                        arrayList.add(tVar);
                    }
                }
                a.k = arrayList;
            }
            if (!(collection2 == null || collection2.isEmpty())) {
                arrayList = new ArrayList();
                for (c cVar : collection2) {
                    if (cVar.b().contains(a.f.b())) {
                        arrayList.add(cVar);
                    }
                }
                a.l = arrayList;
            }
        }
        return a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        aj ajVar = (aj) obj;
        if (this.d == ajVar.d && this.e == ajVar.e && this.b.equals(ajVar.b) && this.c.equals(ajVar.c) && this.f.equals(ajVar.f) && this.i.equals(ajVar.i) && this.k.equals(ajVar.k) && this.l.equals(ajVar.l)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((this.b.hashCode() * 31) + this.c.hashCode()) * 31) + this.d) * 31) + ((int) (this.e ^ (this.e >>> 32)))) * 31) + this.f.hashCode()) * 31) + this.i.hashCode()) * 31) + this.k.hashCode()) * 31) + this.l.hashCode();
    }
}
