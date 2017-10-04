package com.google.a.f.a;

import com.google.a.c.g;
import com.google.a.e;
import com.google.a.f.c;
import com.google.a.i.a.i;
import com.google.a.m;
import com.google.a.q;
import com.google.a.r;
import com.google.a.s;
import com.google.a.t;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class a extends com.google.a.i.a implements c {
    private static final r[] a = new r[0];
    private static final t[] b = new t[0];

    private static final class a implements Serializable, Comparator<r> {
        private a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((r) obj, (r) obj2);
        }

        public int a(r rVar, r rVar2) {
            int intValue = ((Integer) rVar.e().get(s.STRUCTURED_APPEND_SEQUENCE)).intValue();
            int intValue2 = ((Integer) rVar2.e().get(s.STRUCTURED_APPEND_SEQUENCE)).intValue();
            if (intValue < intValue2) {
                return -1;
            }
            if (intValue > intValue2) {
                return 1;
            }
            return 0;
        }
    }

    public r[] a_(com.google.a.c cVar) throws m {
        return a_(cVar, null);
    }

    public r[] a_(com.google.a.c cVar, Map<e, ?> map) throws m {
        List arrayList = new ArrayList();
        for (g gVar : new com.google.a.f.a.a.a(cVar.c()).a(map)) {
            try {
                com.google.a.c.e a = b().a(gVar.d(), (Map) map);
                t[] e = gVar.e();
                if (a.g() instanceof i) {
                    ((i) a.g()).a(e);
                }
                r rVar = new r(a.b(), a.a(), e, com.google.a.a.QR_CODE);
                List c = a.c();
                if (c != null) {
                    rVar.a(s.BYTE_SEGMENTS, c);
                }
                String d = a.d();
                if (d != null) {
                    rVar.a(s.ERROR_CORRECTION_LEVEL, d);
                }
                if (a.h()) {
                    rVar.a(s.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(a.j()));
                    rVar.a(s.STRUCTURED_APPEND_PARITY, Integer.valueOf(a.i()));
                }
                arrayList.add(rVar);
            } catch (q e2) {
            }
        }
        if (arrayList.isEmpty()) {
            return a;
        }
        List a2 = a(arrayList);
        return (r[]) a2.toArray(new r[a2.size()]);
    }

    private static List<r> a(List<r> list) {
        int i;
        for (r e : list) {
            r e2;
            if (e2.e().containsKey(s.STRUCTURED_APPEND_SEQUENCE)) {
                i = 1;
                break;
            }
        }
        i = 0;
        if (i == 0) {
            return list;
        }
        List<r> arrayList = new ArrayList();
        List<r> arrayList2 = new ArrayList();
        for (r e22 : list) {
            arrayList.add(e22);
            if (e22.e().containsKey(s.STRUCTURED_APPEND_SEQUENCE)) {
                arrayList2.add(e22);
            }
        }
        Collections.sort(arrayList2, new a());
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = 0;
        int i3 = 0;
        for (r e222 : arrayList2) {
            stringBuilder.append(e222.a());
            i3 += e222.b().length;
            if (e222.e().containsKey(s.BYTE_SEGMENTS)) {
                for (byte[] length : (Iterable) e222.e().get(s.BYTE_SEGMENTS)) {
                    i2 += length.length;
                }
            }
            i2 = i2;
        }
        Object obj = new byte[i3];
        Object obj2 = new byte[i2];
        i3 = 0;
        int i4 = 0;
        for (r e2222 : arrayList2) {
            System.arraycopy(e2222.b(), 0, obj, i4, e2222.b().length);
            i4 += e2222.b().length;
            if (e2222.e().containsKey(s.BYTE_SEGMENTS)) {
                for (byte[] length2 : (Iterable) e2222.e().get(s.BYTE_SEGMENTS)) {
                    System.arraycopy(length2, 0, obj2, i3, length2.length);
                    i3 += length2.length;
                }
            }
            i3 = i3;
        }
        e2222 = new r(stringBuilder.toString(), obj, b, com.google.a.a.QR_CODE);
        if (i2 > 0) {
            Collection arrayList3 = new ArrayList();
            arrayList3.add(obj2);
            e2222.a(s.BYTE_SEGMENTS, arrayList3);
        }
        arrayList.add(e2222);
        return arrayList;
    }
}
