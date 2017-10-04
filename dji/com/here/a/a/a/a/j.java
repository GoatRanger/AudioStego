package com.here.a.a.a.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class j {
    public final ad<Integer> a;
    public final ad<Integer> b;
    public final ad<Integer> c;
    public final ad<Integer> d;
    private List<d> e;

    public j(List<d> list, Integer num, Integer num2, Integer num3, Integer num4) {
        if (list == null) {
            list = Collections.emptyList();
        }
        this.e = list;
        this.a = ad.b(num);
        this.b = ad.b(num2);
        this.c = ad.b(num3);
        this.d = ad.b(num4);
    }

    public List<d> a() {
        return Collections.unmodifiableList(this.e);
    }

    public static j fromJSON(o oVar) {
        List list;
        Integer num = null;
        if (oVar.b("AltDep")) {
            list = null;
        } else {
            p d = oVar.d("AltDep");
            list = new ArrayList(d.a());
            Iterator it = d.iterator();
            while (it.hasNext()) {
                list.add(d.fromJSON((o) it.next()));
            }
        }
        Integer j = oVar.b("@max") ? null : oVar.j("@max");
        Integer j2 = oVar.b("@maxRT") ? null : oVar.j("@maxRT");
        Integer j3 = oVar.b("@min") ? null : oVar.j("@min");
        if (!oVar.b("@minRT")) {
            num = oVar.j("@minRT");
        }
        return new j(list, j, j2, j3, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        j jVar = (j) obj;
        if (this.a.equals(jVar.a) && this.b.equals(jVar.b) && this.c.equals(jVar.c) && this.d.equals(jVar.d) && this.e.equals(jVar.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }
}
