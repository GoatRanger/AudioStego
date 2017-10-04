package com.here.a.a.a.a;

import com.here.a.a.a.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ab {
    private List<k> a;
    private Collection<ac> b;
    private Collection<s> c;
    private Collection<t> d;

    public ab(List<k> list, Collection<ac> collection, Collection<s> collection2, Collection<t> collection3) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Should contain at least one Departure.");
        }
        if (collection == null) {
            collection = Collections.emptyList();
        }
        if (collection2 == null) {
            collection2 = Collections.emptyList();
        }
        if (collection3 == null) {
            collection3 = Collections.emptyList();
        }
        this.a = list;
        this.b = collection;
        this.c = collection2;
        this.d = collection3;
    }

    public Collection<ac> a() {
        return Collections.unmodifiableCollection(this.b);
    }

    public List<k> b() {
        return Collections.unmodifiableList(this.a);
    }

    public Collection<s> c() {
        return Collections.unmodifiableCollection(this.c);
    }

    public Collection<t> d() {
        return Collections.unmodifiableCollection(this.d);
    }

    public static ab a(o oVar, i iVar) {
        o c = oVar.c("NextDepartures");
        p d = c.d("Dep");
        List<k> arrayList = new ArrayList(d.a());
        Iterator it = d.iterator();
        while (it.hasNext()) {
            arrayList.add(k.fromJSON((o) it.next()));
        }
        Collection hashSet = new HashSet();
        for (k kVar : arrayList) {
            if (kVar.c.c()) {
                s sVar = (s) kVar.c.b();
                sVar.a(iVar.g());
                hashSet.add(sVar);
            }
        }
        return new ab(arrayList, ai.b(c), hashSet, ai.c(c));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ab abVar = (ab) obj;
        if (this.a.equals(abVar.a) && this.b.equals(abVar.b) && this.c.equals(abVar.c) && this.d.equals(abVar.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }
}
