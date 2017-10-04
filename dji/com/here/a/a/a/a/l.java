package com.here.a.a.a.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class l {
    public final int a;
    public final int b;
    public final int c;
    private final List<ak> d;
    private final List<s> e;

    public l(int i, int i2, int i3, List<ak> list, List<s> list2) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        if (list == null) {
            list = Collections.emptyList();
        }
        this.d = list;
        if (list2 == null) {
            list2 = Collections.emptyList();
        }
        this.e = list2;
    }

    public Collection<ak> a() {
        return Collections.unmodifiableCollection(this.d);
    }

    public Collection<s> b() {
        return Collections.unmodifiableCollection(this.e);
    }

    public static l fromJSON(o oVar) {
        List list;
        Iterator it;
        List list2;
        p e = oVar.e("Stn");
        if (e == null || e.a() <= 0) {
            list = null;
        } else {
            list = new ArrayList(e.a());
            it = e.iterator();
            while (it.hasNext()) {
                list.add(ak.fromJSON((o) it.next()));
            }
        }
        o f = oVar.f("Lines");
        if (f != null) {
            e = f.e("Line");
            if (e != null && e.a() > 0) {
                List arrayList = new ArrayList(e.a());
                it = e.iterator();
                while (it.hasNext()) {
                    arrayList.add(s.fromJSON((o) it.next()));
                }
                list2 = arrayList;
                return new l(oVar.j("@lines").intValue(), oVar.j("@radius").intValue(), oVar.j("@stops").intValue(), list, list2);
            }
        }
        list2 = null;
        return new l(oVar.j("@lines").intValue(), oVar.j("@radius").intValue(), oVar.j("@stops").intValue(), list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        if (this.a == lVar.a && this.b == lVar.b && this.c == lVar.c && this.d.equals(lVar.d) && this.e.equals(lVar.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }
}
