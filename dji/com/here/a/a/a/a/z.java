package com.here.a.a.a.a;

import com.here.a.a.a.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class z {
    private final List<am> a;
    private final Collection<s> b;

    public z(List<am> list, Collection<s> collection) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Should contain at least one station with departures.");
        }
        if (collection == null) {
            collection = Collections.emptyList();
        }
        this.a = list;
        this.b = collection;
    }

    public List<am> a() {
        return Collections.unmodifiableList(this.a);
    }

    public Collection<s> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public static z a(o oVar, i iVar) {
        p d = oVar.c("MultiNextDepartures").d("MultiNextDeparture");
        List<am> arrayList = new ArrayList(d.a());
        Iterator it = d.iterator();
        while (it.hasNext()) {
            arrayList.add(am.a((o) it.next(), iVar));
        }
        Collection hashSet = new HashSet();
        for (am b : arrayList) {
            hashSet.addAll(b.b().c());
        }
        return new z(arrayList, hashSet);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        z zVar = (z) obj;
        if (this.a.equals(zVar.a) && this.b.equals(zVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }
}
