package com.here.a.a.a.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class y {
    public final ad<i> a;
    private final List<ac> b;
    private final List<s> c;

    public y(i iVar, List<ac> list, List<s> list2) {
        this.a = ad.b(iVar);
        if (list == null) {
            list = Collections.emptyList();
        }
        this.b = list;
        if (list2 == null) {
            list2 = Collections.emptyList();
        }
        this.c = list2;
    }

    public Collection<s> a() {
        return Collections.unmodifiableCollection(this.c);
    }

    public Collection<ac> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public static y fromJSON(o oVar) {
        List list;
        List list2;
        i iVar = null;
        p e = oVar.e("Op");
        if (e == null || e.a() <= 0) {
            list = null;
        } else {
            list = new ArrayList(e.a());
            Iterator it = e.iterator();
            while (it.hasNext()) {
                list.add(ac.fromJSON((o) it.next()));
            }
        }
        e = oVar.e("Line");
        if (e == null || e.a() <= 0) {
            list2 = null;
        } else {
            List arrayList = new ArrayList(e.a());
            Iterator it2 = e.iterator();
            while (it2.hasNext()) {
                arrayList.add(s.fromJSON((o) it2.next()));
            }
            list2 = arrayList;
        }
        if (!oVar.b("@type")) {
            iVar = i.a(oVar.i("@type"));
        }
        return new y(iVar, list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        y yVar = (y) obj;
        if (this.a.equals(yVar.a) && this.b.equals(yVar.b) && this.c.equals(yVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }
}
