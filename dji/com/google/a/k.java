package com.google.a;

import com.google.a.a.b;
import com.google.a.g.o;
import com.google.a.i.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class k implements p {
    private Map<e, ?> a;
    private p[] b;

    public r a(c cVar) throws m {
        a(null);
        return c(cVar);
    }

    public r a(c cVar, Map<e, ?> map) throws m {
        a((Map) map);
        return c(cVar);
    }

    public r b(c cVar) throws m {
        if (this.b == null) {
            a(null);
        }
        return c(cVar);
    }

    public void a(Map<e, ?> map) {
        Object obj = null;
        this.a = map;
        Object obj2 = (map == null || !map.containsKey(e.TRY_HARDER)) ? null : 1;
        Collection collection = map == null ? null : (Collection) map.get(e.POSSIBLE_FORMATS);
        Collection arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(a.UPC_A) || collection.contains(a.UPC_E) || collection.contains(a.EAN_13) || collection.contains(a.EAN_8) || collection.contains(a.CODABAR) || collection.contains(a.CODE_39) || collection.contains(a.CODE_93) || collection.contains(a.CODE_128) || collection.contains(a.ITF) || collection.contains(a.RSS_14) || collection.contains(a.RSS_EXPANDED)) {
                obj = 1;
            }
            if (obj != null && obj2 == null) {
                arrayList.add(new o(map));
            }
            if (collection.contains(a.QR_CODE)) {
                arrayList.add(new a());
            }
            if (collection.contains(a.DATA_MATRIX)) {
                arrayList.add(new com.google.a.d.a());
            }
            if (collection.contains(a.AZTEC)) {
                arrayList.add(new b());
            }
            if (collection.contains(a.PDF_417)) {
                arrayList.add(new com.google.a.h.b());
            }
            if (collection.contains(a.MAXICODE)) {
                arrayList.add(new com.google.a.e.a());
            }
            if (!(obj == null || obj2 == null)) {
                arrayList.add(new o(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (obj2 == null) {
                arrayList.add(new o(map));
            }
            arrayList.add(new a());
            arrayList.add(new com.google.a.d.a());
            arrayList.add(new b());
            arrayList.add(new com.google.a.h.b());
            arrayList.add(new com.google.a.e.a());
            if (obj2 != null) {
                arrayList.add(new o(map));
            }
        }
        this.b = (p[]) arrayList.toArray(new p[arrayList.size()]);
    }

    public void a() {
        if (this.b != null) {
            for (p a : this.b) {
                a.a();
            }
        }
    }

    private r c(c cVar) throws m {
        if (this.b != null) {
            p[] pVarArr = this.b;
            int length = pVarArr.length;
            int i = 0;
            while (i < length) {
                try {
                    return pVarArr[i].a(cVar, this.a);
                } catch (q e) {
                    i++;
                }
            }
        }
        throw m.a();
    }
}
