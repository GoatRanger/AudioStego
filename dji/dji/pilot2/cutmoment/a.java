package dji.pilot2.cutmoment;

import com.here.odnp.config.OdnpConfigStatic;

public class a {
    public static long a = OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL;
    public static long b = 1000;
    public static long c = (a + b);
    public static long d = 300500;
    public long e;
    public long f;
    private final String g = "DJICutPointFromProject";

    public static int a(long j, long j2, b bVar, a aVar) {
        long j3 = 0;
        long j4 = j2 - a;
        long j5 = b + j2;
        if (j4 < 0) {
            j5 = c;
        } else {
            j3 = j4;
        }
        if (j5 > j) {
            j3 = j - c;
            j5 = j;
        }
        return b(j3, j5, bVar, aVar);
    }

    public static int b(long j, long j2, b bVar, a aVar) {
        if (bVar.c().size() == 0) {
            if (aVar != null) {
                aVar.e = j;
                aVar.f = j2;
                bVar.c().add(aVar);
            } else {
                a aVar2 = new a();
                aVar2.e = j;
                aVar2.f = j2;
                bVar.c().add(aVar2);
            }
            bVar.a(true);
            bVar.b();
            return 0;
        }
        int size = bVar.c().size();
        for (int i = 0; i < size; i++) {
            aVar2 = (a) bVar.c().get(i);
            a aVar3 = null;
            if (size > i + 1) {
                aVar3 = (a) bVar.c().get(i + 1);
            }
            if (aVar2.f >= j) {
                if (aVar2.e <= j) {
                    if (aVar2.f >= j2) {
                        return 0;
                    }
                    aVar2.f = j2;
                    bVar.a(true);
                    if (aVar3 != null && aVar3.e <= j2) {
                        aVar2.f = aVar3.f;
                        bVar.c().remove(i + 1);
                    }
                    if (aVar != null) {
                        aVar.e = aVar2.e;
                        aVar.f = aVar2.f;
                    }
                    return 1;
                } else if (j2 >= aVar2.e) {
                    aVar2.e = j;
                    if (j2 > aVar2.f) {
                        aVar2.f = j2;
                    }
                    bVar.a(true);
                    if (aVar != null) {
                        aVar.e = aVar2.e;
                        aVar.f = aVar2.f;
                    }
                    return 1;
                }
            }
        }
        if (aVar != null) {
            aVar.e = j;
            aVar.f = j2;
            bVar.c().add(aVar);
        } else {
            aVar2 = new a();
            aVar2.e = j;
            aVar2.f = j2;
            bVar.c().add(aVar2);
        }
        bVar.a(true);
        bVar.b();
        return 0;
    }
}
