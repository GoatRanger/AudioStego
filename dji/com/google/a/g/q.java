package com.google.a.g;

import com.google.a.c;
import com.google.a.c.a;
import com.google.a.d;
import com.google.a.e;
import com.google.a.h;
import com.google.a.m;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import com.google.a.t;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public abstract class q implements p {
    protected static final int c = 8;
    protected static final int d = 256;

    public abstract r a(int i, a aVar, Map<e, ?> map) throws m, d, h;

    public r a(c cVar) throws m, h {
        return a(cVar, null);
    }

    public r a(c cVar, Map<e, ?> map) throws m, h {
        try {
            return b(cVar, map);
        } catch (m e) {
            Object obj = (map == null || !map.containsKey(e.TRY_HARDER)) ? null : 1;
            if (obj == null || !cVar.e()) {
                throw e;
            }
            int i;
            c f = cVar.f();
            r b = b(f, map);
            Map e2 = b.e();
            if (e2 == null || !e2.containsKey(s.ORIENTATION)) {
                i = 270;
            } else {
                i = (((Integer) e2.get(s.ORIENTATION)).intValue() + 270) % 360;
            }
            b.a(s.ORIENTATION, Integer.valueOf(i));
            t[] c = b.c();
            if (c != null) {
                int b2 = f.b();
                for (i = 0; i < c.length; i++) {
                    c[i] = new t((((float) b2) - c[i].b()) - 1.0f, c[i].a());
                }
            }
            return b;
        }
    }

    public void a() {
    }

    private r b(c cVar, Map<e, ?> map) throws m {
        Object obj;
        int max;
        int i;
        a aVar;
        Map map2;
        int i2;
        int i3;
        int i4;
        Map enumMap;
        r a;
        t[] c;
        int a2 = cVar.a();
        int b = cVar.b();
        a aVar2 = new a(a2);
        int i5 = b >> 1;
        if (map != null) {
            if (map.containsKey(e.TRY_HARDER)) {
                obj = 1;
                max = Math.max(1, b >> (obj == null ? 8 : 5));
                if (obj == null) {
                    i = b;
                } else {
                    i = 15;
                }
                aVar = aVar2;
                map2 = map;
                for (i2 = 0; i2 < i; i2++) {
                    i3 = (i2 + 1) >> 1;
                    if (((i2 & 1) != 0 ? 1 : null) == null) {
                        i3 = -i3;
                    }
                    i4 = i5 + (i3 * max);
                    if (i4 < 0 || i4 >= b) {
                        break;
                    }
                    try {
                        aVar = cVar.a(i4, aVar);
                        i3 = 0;
                        while (i3 < 2) {
                            if (i3 == 1) {
                                aVar.e();
                                if (map2 != null && map2.containsKey(e.NEED_RESULT_POINT_CALLBACK)) {
                                    enumMap = new EnumMap(e.class);
                                    enumMap.putAll(map2);
                                    enumMap.remove(e.NEED_RESULT_POINT_CALLBACK);
                                    a = a(i4, aVar, enumMap);
                                    if (i3 == 1) {
                                        a.a(s.ORIENTATION, Integer.valueOf(180));
                                        c = a.c();
                                        if (c != null) {
                                            c[0] = new t((((float) a2) - c[0].a()) - 1.0f, c[0].b());
                                            c[1] = new t((((float) a2) - c[1].a()) - 1.0f, c[1].b());
                                        }
                                    }
                                    return a;
                                }
                            }
                            enumMap = map2;
                            try {
                                a = a(i4, aVar, enumMap);
                                if (i3 == 1) {
                                    a.a(s.ORIENTATION, Integer.valueOf(180));
                                    c = a.c();
                                    if (c != null) {
                                        c[0] = new t((((float) a2) - c[0].a()) - 1.0f, c[0].b());
                                        c[1] = new t((((float) a2) - c[1].a()) - 1.0f, c[1].b());
                                    }
                                }
                                return a;
                            } catch (com.google.a.q e) {
                                i3++;
                                map2 = enumMap;
                            }
                        }
                        continue;
                    } catch (m e2) {
                    }
                }
                throw m.a();
            }
        }
        obj = null;
        if (obj == null) {
        }
        max = Math.max(1, b >> (obj == null ? 8 : 5));
        if (obj == null) {
            i = 15;
        } else {
            i = b;
        }
        aVar = aVar2;
        map2 = map;
        for (i2 = 0; i2 < i; i2++) {
            i3 = (i2 + 1) >> 1;
            if ((i2 & 1) != 0) {
            }
            if (((i2 & 1) != 0 ? 1 : null) == null) {
                i3 = -i3;
            }
            i4 = i5 + (i3 * max);
            aVar = cVar.a(i4, aVar);
            i3 = 0;
            while (i3 < 2) {
                if (i3 == 1) {
                    aVar.e();
                    enumMap = new EnumMap(e.class);
                    enumMap.putAll(map2);
                    enumMap.remove(e.NEED_RESULT_POINT_CALLBACK);
                    a = a(i4, aVar, enumMap);
                    if (i3 == 1) {
                        a.a(s.ORIENTATION, Integer.valueOf(180));
                        c = a.c();
                        if (c != null) {
                            c[0] = new t((((float) a2) - c[0].a()) - 1.0f, c[0].b());
                            c[1] = new t((((float) a2) - c[1].a()) - 1.0f, c[1].b());
                        }
                    }
                    return a;
                }
                enumMap = map2;
                a = a(i4, aVar, enumMap);
                if (i3 == 1) {
                    a.a(s.ORIENTATION, Integer.valueOf(180));
                    c = a.c();
                    if (c != null) {
                        c[0] = new t((((float) a2) - c[0].a()) - 1.0f, c[0].b());
                        c[1] = new t((((float) a2) - c[1].a()) - 1.0f, c[1].b());
                    }
                }
                return a;
            }
            continue;
        }
        throw m.a();
    }

    protected static void a(a aVar, int i, int[] iArr) throws m {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int a = aVar.a();
        if (i >= a) {
            throw m.a();
        }
        int i2;
        int i3 = !aVar.a(i) ? 1 : 0;
        int i4 = 0;
        while (i < a) {
            if ((aVar.a(i) ^ i3) != 0) {
                iArr[i4] = iArr[i4] + 1;
                i2 = i3;
            } else {
                i2 = i4 + 1;
                if (i2 == length) {
                    break;
                }
                iArr[i2] = 1;
                int i5 = i2;
                i2 = i3 == 0 ? 1 : 0;
                i4 = i5;
            }
            i++;
            i3 = i2;
        }
        i2 = i4;
        if (i2 == length) {
            return;
        }
        if (i2 != length - 1 || i != a) {
            throw m.a();
        }
    }

    protected static void b(a aVar, int i, int[] iArr) throws m {
        int length = iArr.length;
        boolean a = aVar.a(i);
        while (i > 0 && length >= 0) {
            i--;
            if (aVar.a(i) != a) {
                length--;
                a = !a;
            }
        }
        if (length >= 0) {
            throw m.a();
        }
        a(aVar, i + 1, iArr);
    }

    protected static int a(int[] iArr, int[] iArr2, int i) {
        int i2;
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        for (i2 = 0; i2 < length; i2++) {
            i4 += iArr[i2];
            i3 += iArr2[i2];
        }
        if (i4 < i3) {
            return Integer.MAX_VALUE;
        }
        int i5 = (i4 << 8) / i3;
        int i6 = (i * i5) >> 8;
        i3 = 0;
        for (i2 = 0; i2 < length; i2++) {
            int i7 = iArr[i2] << 8;
            int i8 = iArr2[i2] * i5;
            i7 = i7 > i8 ? i7 - i8 : i8 - i7;
            if (i7 > i6) {
                return Integer.MAX_VALUE;
            }
            i3 += i7;
        }
        return i3 / i4;
    }
}
