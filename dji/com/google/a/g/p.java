package com.google.a.g;

import com.google.a.a;
import com.google.a.e;
import com.google.a.m;
import com.google.a.q;
import com.google.a.r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class p extends q {
    private final x[] a;

    public p(Map<e, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(e.POSSIBLE_FORMATS);
        Collection arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(a.EAN_13)) {
                arrayList.add(new h());
            } else if (collection.contains(a.UPC_A)) {
                arrayList.add(new s());
            }
            if (collection.contains(a.EAN_8)) {
                arrayList.add(new j());
            }
            if (collection.contains(a.UPC_E)) {
                arrayList.add(new z());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new h());
            arrayList.add(new j());
            arrayList.add(new z());
        }
        this.a = (x[]) arrayList.toArray(new x[arrayList.size()]);
    }

    public r a(int i, com.google.a.c.a aVar, Map<e, ?> map) throws m {
        int[] a = x.a(aVar);
        x[] xVarArr = this.a;
        int length = xVarArr.length;
        int i2 = 0;
        while (i2 < length) {
            try {
                r a2 = xVarArr[i2].a(i, aVar, a, (Map) map);
                int i3 = (a2.d() == a.EAN_13 && a2.a().charAt(0) == '0') ? 1 : 0;
                Collection collection = map == null ? null : (Collection) map.get(e.POSSIBLE_FORMATS);
                if (collection == null || collection.contains(a.UPC_A)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (i3 == 0 || r0 == 0) {
                    return a2;
                }
                r rVar = new r(a2.a().substring(1), a2.b(), a2.c(), a.UPC_A);
                rVar.a(a2.e());
                return rVar;
            } catch (q e) {
                i2++;
            }
        }
        throw m.a();
    }

    public void a() {
        for (com.google.a.p a : this.a) {
            a.a();
        }
    }
}
