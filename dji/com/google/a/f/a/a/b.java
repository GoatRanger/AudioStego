package com.google.a.f.a.a;

import com.google.a.i.b.d;
import com.google.a.i.b.e;
import com.google.a.i.b.f;
import com.google.a.m;
import com.google.a.t;
import com.google.a.u;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

final class b extends e {
    private static final f[] c = new f[0];
    private static final float d = 180.0f;
    private static final float e = 9.0f;
    private static final float f = 0.05f;
    private static final float g = 0.5f;

    private static final class a implements Serializable, Comparator<d> {
        private a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((d) obj, (d) obj2);
        }

        public int a(d dVar, d dVar2) {
            float c = dVar2.c() - dVar.c();
            if (((double) c) < 0.0d) {
                return -1;
            }
            return ((double) c) > 0.0d ? 1 : 0;
        }
    }

    b(com.google.a.c.b bVar) {
        super(bVar);
    }

    b(com.google.a.c.b bVar, u uVar) {
        super(bVar, uVar);
    }

    private d[][] c() throws m {
        List b = b();
        int size = b.size();
        if (size < 3) {
            throw m.a();
        } else if (size == 3) {
            d[][] dVarArr = new d[1][];
            dVarArr[0] = new d[]{(d) b.get(0), (d) b.get(1), (d) b.get(2)};
            return dVarArr;
        } else {
            Collections.sort(b, new a());
            List arrayList = new ArrayList();
            for (int i = 0; i < size - 2; i++) {
                d dVar = (d) b.get(i);
                if (dVar != null) {
                    for (int i2 = i + 1; i2 < size - 1; i2++) {
                        d dVar2 = (d) b.get(i2);
                        if (dVar2 != null) {
                            float c = (dVar.c() - dVar2.c()) / Math.min(dVar.c(), dVar2.c());
                            if (Math.abs(dVar.c() - dVar2.c()) > 0.5f && c >= f) {
                                break;
                            }
                            for (int i3 = i2 + 1; i3 < size; i3++) {
                                d dVar3 = (d) b.get(i3);
                                if (dVar3 != null) {
                                    float c2 = (dVar2.c() - dVar3.c()) / Math.min(dVar2.c(), dVar3.c());
                                    if (Math.abs(dVar2.c() - dVar3.c()) > 0.5f && c2 >= f) {
                                        break;
                                    }
                                    Object obj = new d[]{dVar, dVar2, dVar3};
                                    t.a(obj);
                                    f fVar = new f(obj);
                                    float a = t.a(fVar.b(), fVar.a());
                                    float a2 = t.a(fVar.c(), fVar.a());
                                    c = t.a(fVar.b(), fVar.c());
                                    float c3 = (a + c) / (dVar.c() * 2.0f);
                                    if (c3 <= 180.0f && c3 >= e && Math.abs((a - c) / Math.min(a, c)) < 0.1f) {
                                        c = (float) Math.sqrt((double) ((c * c) + (a * a)));
                                        if (Math.abs((a2 - c) / Math.min(a2, c)) < 0.1f) {
                                            arrayList.add(obj);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                return (d[][]) arrayList.toArray(new d[arrayList.size()][]);
            }
            throw m.a();
        }
    }

    public f[] a(Map<com.google.a.e, ?> map) throws m {
        Object obj = (map == null || !map.containsKey(com.google.a.e.TRY_HARDER)) ? null : 1;
        boolean z = map != null && map.containsKey(com.google.a.e.PURE_BARCODE);
        com.google.a.c.b a = a();
        int g = a.g();
        int f = a.f();
        int i = (int) ((((float) g) / 228.0f) * 3.0f);
        if (i < 3 || obj != null) {
            i = 3;
        }
        int[] iArr = new int[5];
        int i2 = i - 1;
        while (i2 < g) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i3 = 0;
            int i4 = 0;
            while (i4 < f) {
                if (a.a(i4, i2)) {
                    if ((i3 & 1) == 1) {
                        i3++;
                    }
                    iArr[i3] = iArr[i3] + 1;
                } else if ((i3 & 1) != 0) {
                    iArr[i3] = iArr[i3] + 1;
                } else if (i3 != 4) {
                    i3++;
                    iArr[i3] = iArr[i3] + 1;
                } else if (e.a(iArr) && a(iArr, i2, i4, z)) {
                    i3 = 0;
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i3 = 3;
                }
                i4++;
            }
            if (e.a(iArr)) {
                a(iArr, i2, f, z);
            }
            i2 += i;
        }
        d[][] c = c();
        List arrayList = new ArrayList();
        for (t[] tVarArr : c) {
            t.a(tVarArr);
            arrayList.add(new f(tVarArr));
        }
        if (arrayList.isEmpty()) {
            return c;
        }
        return (f[]) arrayList.toArray(new f[arrayList.size()]);
    }
}
