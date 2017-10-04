package com.here.a.a.a.a;

import com.here.a.a.a.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class al implements Iterable<ak> {
    private List<ak> a;
    private Collection<s> b;

    public al(List<ak> list, Collection<s> collection) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Stations list can't be null or empty.");
        }
        if (collection == null) {
            collection = Collections.emptyList();
        }
        this.a = list;
        this.b = collection;
    }

    public boolean a() {
        return this.a.isEmpty();
    }

    public Collection<s> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public static al a(o oVar, i iVar) {
        p d = oVar.c("Stations").d("Stn");
        List<ak> arrayList = new ArrayList(d.a());
        Iterator it = d.iterator();
        while (it.hasNext()) {
            arrayList.add(ak.fromJSON((o) it.next()));
        }
        Collection hashSet = new HashSet();
        for (ak a : arrayList) {
            Collection<s> a2 = a.a();
            for (s a3 : a2) {
                a3.a(iVar.g());
            }
            hashSet.addAll(a2);
        }
        return new al(arrayList, hashSet);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        al alVar = (al) obj;
        boolean z = this.a.equals(alVar.a) && this.b.equals(alVar.b) && super.equals(obj);
        return z;
    }

    public int hashCode() {
        return (((super.hashCode() * 31) + this.a.hashCode()) * 31) + this.b.hashCode();
    }

    public Iterator<ak> iterator() {
        return this.a.iterator();
    }
}
