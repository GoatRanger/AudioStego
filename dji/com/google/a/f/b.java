package com.google.a.f;

import com.google.a.c;
import com.google.a.e;
import com.google.a.m;
import com.google.a.p;
import com.google.a.q;
import com.google.a.r;
import com.google.a.t;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class b implements c {
    private static final int a = 100;
    private static final int b = 4;
    private final p c;

    public b(p pVar) {
        this.c = pVar;
    }

    public r[] a_(c cVar) throws m {
        return a_(cVar, null);
    }

    public r[] a_(c cVar, Map<e, ?> map) throws m {
        List arrayList = new ArrayList();
        a(cVar, map, arrayList, 0, 0, 0);
        if (!arrayList.isEmpty()) {
            return (r[]) arrayList.toArray(new r[arrayList.size()]);
        }
        throw m.a();
    }

    private void a(c cVar, Map<e, ?> map, List<r> list, int i, int i2, int i3) {
        if (i3 <= 4) {
            try {
                Object obj;
                r a = this.c.a(cVar, map);
                for (r a2 : list) {
                    if (a2.a().equals(a.a())) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    list.add(a(a, i, i2));
                }
                t[] c = a.c();
                if (c != null && c.length != 0) {
                    int a3 = cVar.a();
                    int b = cVar.b();
                    float f = (float) a3;
                    float f2 = (float) b;
                    float f3 = 0.0f;
                    float f4 = 0.0f;
                    int length = c.length;
                    int i4 = 0;
                    while (i4 < length) {
                        float f5;
                        t tVar = c[i4];
                        float a4 = tVar.a();
                        float b2 = tVar.b();
                        if (a4 < f) {
                            f5 = a4;
                        } else {
                            f5 = f;
                        }
                        if (b2 < f2) {
                            f = b2;
                        } else {
                            f = f2;
                        }
                        if (a4 <= f3) {
                            a4 = f3;
                        }
                        if (b2 <= f4) {
                            b2 = f4;
                        }
                        i4++;
                        f4 = b2;
                        f3 = a4;
                        f2 = f;
                        f = f5;
                    }
                    if (f > DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) {
                        a(cVar.a(0, 0, (int) f, b), map, list, i, i2, i3 + 1);
                    }
                    if (f2 > DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) {
                        a(cVar.a(0, 0, a3, (int) f2), map, list, i, i2, i3 + 1);
                    }
                    if (f3 < ((float) (a3 - 100))) {
                        a(cVar.a((int) f3, 0, a3 - ((int) f3), b), map, list, i + ((int) f3), i2, i3 + 1);
                    }
                    if (f4 < ((float) (b - 100))) {
                        a(cVar.a(0, (int) f4, a3, b - ((int) f4)), map, list, i, i2 + ((int) f4), i3 + 1);
                    }
                }
            } catch (q e) {
            }
        }
    }

    private static r a(r rVar, int i, int i2) {
        t[] c = rVar.c();
        if (c == null) {
            return rVar;
        }
        t[] tVarArr = new t[c.length];
        for (int i3 = 0; i3 < c.length; i3++) {
            t tVar = c[i3];
            tVarArr[i3] = new t(tVar.a() + ((float) i), tVar.b() + ((float) i2));
        }
        r rVar2 = new r(rVar.a(), rVar.b(), tVarArr, rVar.d());
        rVar2.a(rVar.e());
        return rVar2;
    }
}
