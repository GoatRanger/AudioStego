package dji.d.a;

import dji.common.battery.DJIBatteryStatus;
import dji.pilot.publics.e.e;
import dji.sdksharedlib.b.a;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class b$1 implements o<Long, d<Boolean>> {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public d<Boolean> a(Long l) {
        b.a(this.a, Integer.valueOf(b.a), a.b);
        b.b(this.a, Integer.valueOf(b.a), a.c);
        b.c(this.a, Integer.valueOf(20), a.d);
        b.d(this.a, Integer.valueOf(b.a * 100), a.e);
        b.e(this.a, Integer.valueOf(b.a), a.f);
        b.f(this.a, Float.valueOf((e.f / ((float) (b.a + 1))) - e.f), a.h);
        b.g(this.a, Integer.valueOf(b.a), a.i);
        b.h(this.a, Integer.valueOf(b.a), a.g);
        b.a++;
        if (b.a > 100) {
            b.a = 0;
        }
        if (b.a <= 60 || b.a >= 80) {
            b.j(this.a, DJIBatteryStatus.NORMAL, a.m);
        } else {
            b.i(this.a, DJIBatteryStatus.INVALID, a.m);
        }
        return d.b(Boolean.valueOf(true));
    }
}
