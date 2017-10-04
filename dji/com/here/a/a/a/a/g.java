package com.here.a.a.a.a;

import com.here.a.a.a.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class g extends h {
    private final List<f> e;

    public g(h hVar, List<f> list) {
        super(hVar);
        if (list == null) {
            this.e = Collections.emptyList();
        } else {
            this.e = list;
        }
    }

    public static g a(o oVar, i iVar) {
        List list = null;
        o f = oVar.c("Coverage").f("NearbyCities");
        if (f != null) {
            p d = f.d("City");
            List arrayList = new ArrayList(d.a());
            Iterator it = d.iterator();
            while (it.hasNext()) {
                arrayList.add(f.fromJSON((o) it.next()));
            }
            list = arrayList;
        }
        return new g(h.b(oVar, iVar), list);
    }

    public List<f> a() {
        return Collections.unmodifiableList(this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        if (super.equals(gVar) && this.e.equals(gVar.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.e.hashCode();
    }
}
