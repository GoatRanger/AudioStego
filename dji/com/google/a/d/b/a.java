package com.google.a.d.b;

import com.google.a.c.a.c;
import com.google.a.c.g;
import com.google.a.c.i;
import com.google.a.m;
import com.google.a.t;
import dji.pilot.usercenter.protocol.d;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class a {
    private final com.google.a.c.b a;
    private final c b;

    private static final class a {
        private final t a;
        private final t b;
        private final int c;

        private a(t tVar, t tVar2, int i) {
            this.a = tVar;
            this.b = tVar2;
            this.c = i;
        }

        t a() {
            return this.a;
        }

        t b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public String toString() {
            return this.a + d.t + this.b + '/' + this.c;
        }
    }

    private static final class b implements Serializable, Comparator<a> {
        private b() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((a) obj, (a) obj2);
        }

        public int a(a aVar, a aVar2) {
            return aVar.c() - aVar2.c();
        }
    }

    public a(com.google.a.c.b bVar) throws m {
        this.a = bVar;
        this.b = new c(bVar);
    }

    public g a() throws m {
        t[] a = this.b.a();
        t tVar = a[0];
        t tVar2 = a[1];
        t tVar3 = a[2];
        t tVar4 = a[3];
        List arrayList = new ArrayList(4);
        arrayList.add(b(tVar, tVar2));
        arrayList.add(b(tVar, tVar3));
        arrayList.add(b(tVar2, tVar4));
        arrayList.add(b(tVar3, tVar4));
        Collections.sort(arrayList, new b());
        a aVar = (a) arrayList.get(0);
        a aVar2 = (a) arrayList.get(1);
        Map hashMap = new HashMap();
        a(hashMap, aVar.a());
        a(hashMap, aVar.b());
        a(hashMap, aVar2.a());
        a(hashMap, aVar2.b());
        t tVar5 = null;
        t tVar6 = null;
        t tVar7 = null;
        for (Entry entry : hashMap.entrySet()) {
            t tVar8;
            t tVar9 = (t) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                tVar8 = tVar9;
                tVar9 = tVar7;
                tVar7 = tVar5;
            } else if (tVar5 == null) {
                tVar8 = tVar6;
                t tVar10 = tVar7;
                tVar7 = tVar9;
                tVar9 = tVar10;
            } else {
                tVar8 = tVar6;
                tVar7 = tVar5;
            }
            tVar6 = tVar8;
            tVar5 = tVar7;
            tVar7 = tVar9;
        }
        if (tVar5 == null || tVar6 == null || tVar7 == null) {
            throw m.a();
        }
        com.google.a.c.b a2;
        a = new t[]{tVar5, tVar6, tVar7};
        t.a(a);
        tVar7 = a[0];
        tVar9 = a[1];
        tVar6 = a[2];
        if (!hashMap.containsKey(tVar)) {
            tVar5 = tVar;
        } else if (!hashMap.containsKey(tVar2)) {
            tVar5 = tVar2;
        } else if (hashMap.containsKey(tVar3)) {
            tVar5 = tVar4;
        } else {
            tVar5 = tVar3;
        }
        int c = b(tVar6, tVar5).c();
        int c2 = b(tVar7, tVar5).c();
        if ((c & 1) == 1) {
            c++;
        }
        c += 2;
        if ((c2 & 1) == 1) {
            c2++;
        }
        int i = c2 + 2;
        int c3;
        if (c * 4 >= i * 7 || i * 4 >= c * 7) {
            tVar4 = a(tVar9, tVar7, tVar6, tVar5, c, i);
            if (tVar4 == null) {
                tVar4 = tVar5;
            }
            c3 = b(tVar6, tVar4).c();
            int c4 = b(tVar7, tVar4).c();
            if ((c3 & 1) == 1) {
                c3++;
            }
            if ((c4 & 1) == 1) {
                c4++;
            }
            a2 = a(this.a, tVar6, tVar9, tVar7, tVar4, c3, c4);
        } else {
            tVar4 = a(tVar9, tVar7, tVar6, tVar5, Math.min(i, c));
            if (tVar4 == null) {
                tVar4 = tVar5;
            }
            c3 = Math.max(b(tVar6, tVar4).c(), b(tVar7, tVar4).c()) + 1;
            if ((c3 & 1) == 1) {
                c3++;
            }
            a2 = a(this.a, tVar6, tVar9, tVar7, tVar4, c3, c3);
        }
        return new g(a2, new t[]{tVar6, tVar9, tVar7, tVar4});
    }

    private t a(t tVar, t tVar2, t tVar3, t tVar4, int i, int i2) {
        float a = ((float) a(tVar, tVar2)) / ((float) i);
        int a2 = a(tVar3, tVar4);
        t tVar5 = new t((((tVar4.a() - tVar3.a()) / ((float) a2)) * a) + tVar4.a(), (a * ((tVar4.b() - tVar3.b()) / ((float) a2))) + tVar4.b());
        float a3 = ((float) a(tVar, tVar3)) / ((float) i2);
        int a4 = a(tVar2, tVar4);
        t tVar6 = new t((((tVar4.a() - tVar2.a()) / ((float) a4)) * a3) + tVar4.a(), (a3 * ((tVar4.b() - tVar2.b()) / ((float) a4))) + tVar4.b());
        if (a(tVar5)) {
            if (!a(tVar6)) {
                return tVar5;
            }
            if (Math.abs(i - b(tVar3, tVar5).c()) + Math.abs(i2 - b(tVar2, tVar5).c()) <= Math.abs(i - b(tVar3, tVar6).c()) + Math.abs(i2 - b(tVar2, tVar6).c())) {
                return tVar5;
            }
            return tVar6;
        } else if (a(tVar6)) {
            return tVar6;
        } else {
            return null;
        }
    }

    private t a(t tVar, t tVar2, t tVar3, t tVar4, int i) {
        float a = ((float) a(tVar, tVar2)) / ((float) i);
        int a2 = a(tVar3, tVar4);
        t tVar5 = new t((((tVar4.a() - tVar3.a()) / ((float) a2)) * a) + tVar4.a(), (a * ((tVar4.b() - tVar3.b()) / ((float) a2))) + tVar4.b());
        float a3 = ((float) a(tVar, tVar3)) / ((float) i);
        int a4 = a(tVar2, tVar4);
        t tVar6 = new t((((tVar4.a() - tVar2.a()) / ((float) a4)) * a3) + tVar4.a(), (a3 * ((tVar4.b() - tVar2.b()) / ((float) a4))) + tVar4.b());
        if (a(tVar5)) {
            return (!a(tVar6) || Math.abs(b(tVar3, tVar5).c() - b(tVar2, tVar5).c()) <= Math.abs(b(tVar3, tVar6).c() - b(tVar2, tVar6).c())) ? tVar5 : tVar6;
        } else {
            if (a(tVar6)) {
                return tVar6;
            }
            return null;
        }
    }

    private boolean a(t tVar) {
        return tVar.a() >= 0.0f && tVar.a() < ((float) this.a.f()) && tVar.b() > 0.0f && tVar.b() < ((float) this.a.g());
    }

    private static int a(t tVar, t tVar2) {
        return com.google.a.c.a.a.a(t.a(tVar, tVar2));
    }

    private static void a(Map<t, Integer> map, t tVar) {
        Integer num = (Integer) map.get(tVar);
        map.put(tVar, Integer.valueOf(num == null ? 1 : num.intValue() + 1));
    }

    private static com.google.a.c.b a(com.google.a.c.b bVar, t tVar, t tVar2, t tVar3, t tVar4, int i, int i2) throws m {
        return i.getInstance().a(bVar, i, i2, dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c, ((float) i) - dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c, ((float) i) - dji.pilot.visual.a.d.c, ((float) i2) - dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c, ((float) i2) - dji.pilot.visual.a.d.c, tVar.a(), tVar.b(), tVar4.a(), tVar4.b(), tVar3.a(), tVar3.b(), tVar2.a(), tVar2.b());
    }

    private a b(t tVar, t tVar2) {
        Object obj;
        int i;
        int a = (int) tVar.a();
        int b = (int) tVar.b();
        int a2 = (int) tVar2.a();
        int b2 = (int) tVar2.b();
        if (Math.abs(b2 - b) > Math.abs(a2 - a)) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            int i2 = b2;
            b2 = a2;
            a2 = i2;
            int i3 = b;
            b = a;
            a = i3;
        }
        int abs = Math.abs(b2 - b);
        int abs2 = Math.abs(a2 - a);
        int i4 = (-abs) >> 1;
        int i5 = a < a2 ? 1 : -1;
        int i6 = b < b2 ? 1 : -1;
        int i7 = 0;
        com.google.a.c.b bVar = this.a;
        if (obj != null) {
            i = a;
        } else {
            i = b;
        }
        boolean a3 = bVar.a(i, obj != null ? b : a);
        int i8 = a;
        int i9 = i4;
        while (b != b2) {
            com.google.a.c.b bVar2 = this.a;
            if (obj != null) {
                i4 = i8;
            } else {
                i4 = b;
            }
            boolean a4 = bVar2.a(i4, obj != null ? b : i8);
            if (a4 != a3) {
                i7++;
                a3 = a4;
            }
            a = i9 + abs2;
            if (a > 0) {
                if (i8 == a2) {
                    a2 = i7;
                    break;
                }
                i8 += i5;
                a -= abs;
            }
            b += i6;
            i9 = a;
        }
        a2 = i7;
        return new a(tVar, tVar2, a2);
    }
}
