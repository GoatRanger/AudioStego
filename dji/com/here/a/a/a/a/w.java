package com.here.a.a.a.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class w {
    public final ad<String> a;
    private List<v> b;

    public w(List<v> list, String str) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Should contain at least one Maneuver!");
        }
        this.b = list;
        this.a = ad.b(str);
    }

    public static w fromJSON(o oVar) {
        p d = oVar.d("Maneuver");
        List arrayList = new ArrayList(d.a());
        Iterator it = d.iterator();
        while (it.hasNext()) {
            arrayList.add(v.fromJSON((o) it.next()));
        }
        return new w(arrayList, oVar.a("@id", null));
    }

    public List<v> a() {
        return Collections.unmodifiableList(this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        w wVar = (w) obj;
        if (this.b.equals(wVar.b) && this.a.equals(wVar.a)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) + this.a.hashCode();
    }
}
