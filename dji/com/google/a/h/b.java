package com.google.a.h;

import com.google.a.d;
import com.google.a.e;
import com.google.a.f.c;
import com.google.a.h;
import com.google.a.h.a.j;
import com.google.a.h.b.a;
import com.google.a.m;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import com.google.a.t;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class b implements c, p {
    public r a(com.google.a.c cVar) throws m, h, d {
        return a(cVar, null);
    }

    public r a(com.google.a.c cVar, Map<e, ?> map) throws m, h, d {
        r[] a = a(cVar, map, false);
        if (a != null && a.length != 0 && a[0] != null) {
            return a[0];
        }
        throw m.a();
    }

    public r[] a_(com.google.a.c cVar) throws m {
        return a_(cVar, null);
    }

    public r[] a_(com.google.a.c cVar, Map<e, ?> map) throws m {
        try {
            return a(cVar, map, true);
        } catch (h e) {
            throw m.a();
        } catch (d e2) {
            throw m.a();
        }
    }

    private static r[] a(com.google.a.c cVar, Map<e, ?> map, boolean z) throws m, h, d {
        List arrayList = new ArrayList();
        com.google.a.h.b.b a = a.a(cVar, (Map) map, z);
        for (t[] tVarArr : a.b()) {
            com.google.a.c.e a2 = j.a(a.a(), tVarArr[4], tVarArr[5], tVarArr[6], tVarArr[7], b(tVarArr), a(tVarArr));
            r rVar = new r(a2.b(), a2.a(), tVarArr, com.google.a.a.PDF_417);
            rVar.a(s.ERROR_CORRECTION_LEVEL, a2.d());
            c cVar2 = (c) a2.g();
            if (cVar2 != null) {
                rVar.a(s.PDF417_EXTRA_METADATA, cVar2);
            }
            arrayList.add(rVar);
        }
        return (r[]) arrayList.toArray(new r[arrayList.size()]);
    }

    private static int a(t tVar, t tVar2) {
        if (tVar == null || tVar2 == null) {
            return 0;
        }
        return (int) Math.abs(tVar.a() - tVar2.a());
    }

    private static int b(t tVar, t tVar2) {
        if (tVar == null || tVar2 == null) {
            return Integer.MAX_VALUE;
        }
        return (int) Math.abs(tVar.a() - tVar2.a());
    }

    private static int a(t[] tVarArr) {
        return Math.max(Math.max(a(tVarArr[0], tVarArr[4]), (a(tVarArr[6], tVarArr[2]) * 17) / 18), Math.max(a(tVarArr[1], tVarArr[5]), (a(tVarArr[7], tVarArr[3]) * 17) / 18));
    }

    private static int b(t[] tVarArr) {
        return Math.min(Math.min(b(tVarArr[0], tVarArr[4]), (b(tVarArr[6], tVarArr[2]) * 17) / 18), Math.min(b(tVarArr[1], tVarArr[5]), (b(tVarArr[7], tVarArr[3]) * 17) / 18));
    }

    public void a() {
    }
}
